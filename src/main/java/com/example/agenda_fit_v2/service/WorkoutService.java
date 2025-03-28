package com.example.agenda_fit_v2.service;

import com.example.agenda_fit_v2.controller.dto.WorkoutDTO;
import com.example.agenda_fit_v2.controller.dto.WorkoutResponseDTO;
import com.example.agenda_fit_v2.controller.dto.WorkoutsExercisesDTO;
import com.example.agenda_fit_v2.controller.dto.WorkoutsExercisesResponseDTO;
import com.example.agenda_fit_v2.entity.Exercises;
import com.example.agenda_fit_v2.entity.Users;
import com.example.agenda_fit_v2.entity.Workouts;
import com.example.agenda_fit_v2.entity.WorkoutsExercises;
import com.example.agenda_fit_v2.exception.ExerciseDoesNotExistException;
import com.example.agenda_fit_v2.exception.UserDoesNotExistException;
import com.example.agenda_fit_v2.exception.WorkoutDoesNotExistException;
import com.example.agenda_fit_v2.repository.ExerciseRepository;
import com.example.agenda_fit_v2.repository.UserRepository;
import com.example.agenda_fit_v2.repository.WorkoutRepository;
import com.example.agenda_fit_v2.repository.WorkoutsExercisesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;
    private UserRepository userRepository;
    private WorkoutsExercisesRepository workoutsExercisesRepository;
    private ExerciseRepository exerciseRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository, WorkoutsExercisesRepository workoutsExercisesRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.workoutsExercisesRepository = workoutsExercisesRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public WorkoutResponseDTO createWorkout(WorkoutDTO workoutDTO) {

        //Valida usuario
        Users user = userRepository.findById(workoutDTO.userId())
                .orElseThrow(() -> new UserDoesNotExistException(workoutDTO.userId()));

        //Criacao do treino
        Workouts workout = new Workouts();
        workout.setTitle(workoutDTO.title());
        workout.setDescription(workoutDTO.description());
        workout.setDate(workoutDTO.date());
        workout.setHours(workoutDTO.hours());
        workout.setType(workoutDTO.type());
        workout.setUser(user);
        workout = workoutRepository.save(workout);

        //Percorre e salva a lista de exercicios na WorkoutsExercises
        List<WorkoutsExercises> workoutsExercisesList = new ArrayList<>();
        for (WorkoutsExercisesDTO exercisesDTO : workoutDTO.workoutsExercises()){

            Exercises exercise = exerciseRepository.findById(exercisesDTO.exerciseId())
                    .orElseThrow(() -> new RuntimeException(""));

            WorkoutsExercises workoutsExercises = new WorkoutsExercises();

            workoutsExercises.setWorkout(workout);
            workoutsExercises.setExercise(exercise);
            workoutsExercises.setWeight(exercisesDTO.weight());
            workoutsExercises.setSets(exercisesDTO.sets());
            workoutsExercises.setRepetitions(exercisesDTO.repetitions());

            workoutsExercisesList.add(workoutsExercises);
        }
        workoutsExercisesRepository.saveAll(workoutsExercisesList);

        workout.setWorkoutExercises(new ArrayList<>(workoutsExercisesList));

        List<WorkoutsExercisesResponseDTO> workoutsExercisesResponseList = workoutsExercisesList.stream()
                .map(we -> new WorkoutsExercisesResponseDTO(
                        we.getExercise().getId_exercise(),
                        we.getWeight(),
                        we.getRepetitions(),
                        we.getSets()))
                .collect(Collectors.toList());

        return new WorkoutResponseDTO(workout.getId_workout(), workout.getTitle(), workout.getDescription(), workoutsExercisesResponseList, user.getIduser());
    }

    @Transactional
    public WorkoutResponseDTO updateWorkout(WorkoutDTO dto, UUID id_workout) {

        // Valida o id do treino
        Workouts workout = workoutRepository.findById(id_workout)
                .orElseThrow(() -> new WorkoutDoesNotExistException(id_workout));

        workout.setTitle(dto.title());
        workout.setDescription(dto.description());
        workout.setDate(dto.date());
        workout.setHours(dto.hours());
        workout.setType(dto.type());

        // limpa as referencias dos exercicios do treino, ao invés de excluir
        workout.getWorkoutExercises().clear();

        // monta a lista com novos exercicios
        List<WorkoutsExercises> workoutsExercisesList = dto.workoutsExercises().stream().map(exercise -> {
            Exercises exerciseDb = exerciseRepository.findById(exercise.exerciseId())
                    .orElseThrow(() -> new ExerciseDoesNotExistException());

            WorkoutsExercises newExercise = new WorkoutsExercises();

            newExercise.setWorkout(workout);
            newExercise.setExercise(exerciseDb);
            newExercise.setRepetitions(exercise.repetitions());
            newExercise.setSets(exercise.sets());
            newExercise.setWeight(exercise.weight());

            return newExercise;
        }).toList();

        // monta a lista do exercicios para o response do controller
        List<WorkoutsExercisesResponseDTO> workoutsExercisesResponseDTO = workoutsExercisesList.stream().map(exercise -> {
            WorkoutsExercisesResponseDTO newExerciseDTO = new WorkoutsExercisesResponseDTO(
                    exercise.getExercise().getId_exercise(),
                    exercise.getWeight(),
                    exercise.getRepetitions(),
                    exercise.getSets()
            );
            return newExerciseDTO;
        }).toList();

        workout.getWorkoutExercises().addAll(workoutsExercisesList);
        workoutRepository.save(workout);
        return new WorkoutResponseDTO(id_workout, workout.getTitle(), workout.getDescription(), workoutsExercisesResponseDTO, workout.getUser().getIduser());
    }

    public List<WorkoutResponseDTO> getWorkoutsByUser(UUID id_user) {
        List<Workouts> workoutsList = workoutRepository.findByUser_Iduser(id_user);
        List<WorkoutResponseDTO> workoutResponseDTOList = new ArrayList<>();

        return workoutsList.stream().map(workout -> new WorkoutResponseDTO<>(
                workout.getId_workout(),
                workout.getTitle(),
                workout.getDescription(),
                workout.getWorkoutExercises().stream()
                        .map(this::convertToWorkoutsExercisesResponseDTO)
                        .collect(Collectors.toList()),
                workout.getUser().getIduser()
        )).collect(Collectors.toList());

    }

    private WorkoutsExercisesResponseDTO convertToWorkoutsExercisesResponseDTO(WorkoutsExercises workoutsExercises) {
        return new WorkoutsExercisesResponseDTO(
                workoutsExercises.getExercise().getId_exercise(),
                workoutsExercises.getWeight(),
                workoutsExercises.getRepetitions(),
                workoutsExercises.getSets()
        );
    }
}
