package com.example.agenda_fit_v2.service;

import com.example.agenda_fit_v2.controller.dto.WorkoutDTO;
import com.example.agenda_fit_v2.controller.dto.WorkoutResponseDTO;
import com.example.agenda_fit_v2.controller.dto.WorkoutsExercisesDTO;
import com.example.agenda_fit_v2.controller.dto.WorkoutsExercisesResponseDTO;
import com.example.agenda_fit_v2.entity.Exercises;
import com.example.agenda_fit_v2.entity.Users;
import com.example.agenda_fit_v2.entity.Workouts;
import com.example.agenda_fit_v2.entity.WorkoutsExercises;
import com.example.agenda_fit_v2.exception.UserDoesNotExistException;
import com.example.agenda_fit_v2.repository.ExerciseRepository;
import com.example.agenda_fit_v2.repository.UserRepository;
import com.example.agenda_fit_v2.repository.WorkoutRepository;
import com.example.agenda_fit_v2.repository.WorkoutsExercisesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        workout.setWorkoutExercises(workoutsExercisesList);

        List<WorkoutsExercisesResponseDTO> workoutsExercisesResponseList = workoutsExercisesList.stream()
                .map(we -> new WorkoutsExercisesResponseDTO(
                        we.getExercise().getId_exercise(),
                        we.getWeight(),
                        we.getRepetitions(),
                        we.getSets()))
                .collect(Collectors.toList());

        return new WorkoutResponseDTO(workout.getId_workout(), workout.getTitle(), workout.getDescription(), workoutsExercisesResponseList);
    }

}
