package hu.elte.webjava.coachassistant.application.service;

import hu.elte.webjava.coachassistant.application.webdomain.UserType;
import hu.elte.webjava.coachassistant.domain.*;
import hu.elte.webjava.coachassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class TestDataGenerator {

    private final UserRepository userRepository;
    private final ClientService clientService;
    private final CalorieDiaryService calorieDiaryService;
    private final WeightDiaryService weightDiaryService;
    private final TrainingPlanService trainingPlanService;
    private final ExerciseService exerciseService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TestDataGenerator(UserRepository userRepository,
                             ClientService clientService,
                             CalorieDiaryService calorieDiaryService,
                             WeightDiaryService weightDiaryService,
                             TrainingPlanService trainingPlanService,
                             ExerciseService exerciseService,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientService = clientService;
        this.calorieDiaryService = calorieDiaryService;
        this.weightDiaryService = weightDiaryService;
        this.trainingPlanService = trainingPlanService;
        this.exerciseService = exerciseService;
        this.passwordEncoder = passwordEncoder;
    }

    public void createTestData() {
        Coach megGyozo = createCoach("Meg", "Győző", "meg.gyozo@teszt.com", "meggyozo");
        TrainingPlan kezdoErosito = createTrainingPlan("Erősítő edzésterv kezdőknek", TrainingType.STRENGTH, 280, 45, Gender.MALE, megGyozo);
        createRepetitiveExercise("Tolódzkodás", 3, 45, TimeUnit.SECONDS, 8, kezdoErosito);
        createRepetitiveExercise("Mellhez vagy háthoz húzás széles fogással", 3, 45, TimeUnit.SECONDS, 8, kezdoErosito);
        createRepetitiveExercise("Kalapács-bicepsz", 3, 45, TimeUnit.SECONDS, 8, kezdoErosito);
        createRepetitiveExercise("Oldalemelés", 3, 45, TimeUnit.SECONDS, 8, kezdoErosito);
        createRepetitiveExercise("Előre emelés", 3, 45, TimeUnit.SECONDS, 8, kezdoErosito);
        createRepetitiveExercise("Guggolás", 3, 45, TimeUnit.SECONDS, 8, kezdoErosito);
        createRepetitiveExercise("Lábtolás", 3, 45, TimeUnit.SECONDS, 8, kezdoErosito);

        TrainingPlan haladoErosito = createTrainingPlan("Erősítő edzésterv haladóknak", TrainingType.STRENGTH, 350, 75, Gender.MALE, megGyozo);
        createRepetitiveExercise("Tolódzkodás", 4, 45, TimeUnit.SECONDS, 10, haladoErosito);
        createRepetitiveExercise("Mellhez vagy háthoz húzás széles fogással", 3, 45, TimeUnit.SECONDS, 10, haladoErosito);
        createRepetitiveExercise("Kalapács-bicepsz", 4, 45, TimeUnit.SECONDS, 10, haladoErosito);
        createRepetitiveExercise("Oldalemelés", 4, 45, TimeUnit.SECONDS, 10, haladoErosito);
        createRepetitiveExercise("Előre emelés", 4, 45, TimeUnit.SECONDS, 10, haladoErosito);
        createRepetitiveExercise("Guggolás", 4, 45, TimeUnit.SECONDS, 10, haladoErosito);
        createRepetitiveExercise("Lábtolás", 4, 45, TimeUnit.SECONDS, 10, haladoErosito);

        TrainingPlan hatEdzes = createTrainingPlan("Hátedzés", TrainingType.STRENGTH, 300, 40, Gender.MALE, megGyozo);
        createRepetitiveExercise("Mellhez vagy háthoz húzás széles fogással", 4, 45, TimeUnit.SECONDS, 10, hatEdzes);
        createRepetitiveExercise("Széles fogású húzodzkodás", 4, 45, TimeUnit.SECONDS, 10, hatEdzes);
        createRepetitiveExercise("T-rudas evezés", 4, 45, TimeUnit.SECONDS, 10, hatEdzes);
        createRepetitiveExercise("Evezés egykezes súllyal", 4, 45, TimeUnit.SECONDS, 10, hatEdzes);
        createRepetitiveExercise("Y-emelés", 4, 45, TimeUnit.SECONDS, 10, hatEdzes);

        Coach gaZora = createCoach("Gá", "Zóra", "ga.zora@teszt.com", "gazora");
        TrainingPlan alakformalo = createTrainingPlan("Alakformáló", TrainingType.CARDIO, 270, 40, Gender.FEMALE, gaZora);
        createIntervalExercise("Futópadon séta", 1, 2, TimeUnit.MINUTES, 10, TimeUnit.MINUTES, alakformalo);
        createRepetitiveExercise("Bicepsz váltott karral", 3, 45, TimeUnit.SECONDS, 10, alakformalo);
        createRepetitiveExercise("Álig húzás", 3, 45, TimeUnit.SECONDS, 10, alakformalo);
        createRepetitiveExercise("Nyakból nyomás", 3, 45, TimeUnit.SECONDS, 10, alakformalo);
        createRepetitiveExercise("Oldalemelés", 3, 45, TimeUnit.SECONDS, 10, alakformalo);
        createRepetitiveExercise("Tricepsz", 3, 45, TimeUnit.SECONDS, 10, alakformalo);
        createRepetitiveExercise("Tricepsz", 3, 45, TimeUnit.SECONDS, 10, alakformalo);
        createIntervalExercise("Futópadon séta", 1, 1, TimeUnit.MINUTES, 5, TimeUnit.MINUTES, alakformalo);

        TrainingPlan kezdoCardio = createTrainingPlan("Kezdő kardió edzés", TrainingType.CARDIO, 230, 35, Gender.FEMALE, gaZora);
        createIntervalExercise("Háttámlás kerékpár könnyű fokozaton", 1, 2, TimeUnit.MINUTES, 10, TimeUnit.MINUTES, alakformalo);
        createIntervalExercise("Háttámlás kerékpár közepes fokozaton", 1, 2, TimeUnit.MINUTES, 15, TimeUnit.MINUTES, alakformalo);
        createIntervalExercise("Háttámlás kerékpár legkönnyebb fokozaton", 1, 1, TimeUnit.MINUTES, 5, TimeUnit.MINUTES, alakformalo);

        TrainingPlan haladoCardio = createTrainingPlan("Haladó kardió edzés", TrainingType.CARDIO, 300, 50, Gender.FEMALE, gaZora);
        createIntervalExercise("Futópad - emelkedő: 0%, sebesség: 3,0 km/h", 1, 2, TimeUnit.MINUTES, 5, TimeUnit.MINUTES, alakformalo);
        createIntervalExercise("Futópad - emelkedő: 0%, sebesség: 4-5 km/h", 1, 2, TimeUnit.MINUTES, 10, TimeUnit.MINUTES, alakformalo);
        createIntervalExercise("Futópad - emelkedő: 5%, sebesség: 4-5 km/h", 1, 2, TimeUnit.MINUTES, 10, TimeUnit.MINUTES, alakformalo);
        createIntervalExercise("Futópad - emelkedő: 2,5%, sebesség: 4-5 km/h", 1, 2, TimeUnit.MINUTES, 10, TimeUnit.MINUTES, alakformalo);
        createIntervalExercise("Futópad - emelkedő: 0%, sebesség: 3 km/h", 1, 2, TimeUnit.MINUTES, 5, TimeUnit.MINUTES, alakformalo);

        Client gipszJakab = createClient("Gipsz", "Jakab", "gipsz.jakab@teszt.com", "gipszjakab",
                LocalDate.of(1998, 5, 27), 179, Gender.MALE);
        createWeightDiaries(LocalDate.of(2022, 11, 18), 81, 30, 3, true, gipszJakab);
        createCalorieDiaries(LocalDate.of(2022, 11, 18), 2700, 2750, 30, gipszJakab);
        clientService.subscribeToTrainingPlan(gipszJakab.getId(), kezdoErosito.getId());

        Client bekrePal = createClient("Bekre", "Pál", "bekre.pal@teszt.com", "bekrepal",
                LocalDate.of(1996, 11, 29), 191, Gender.MALE);
        createWeightDiaries(LocalDate.of(2022, 11, 18), 94, 30, 4, true, bekrePal);
        createCalorieDiaries(LocalDate.of(2022, 11, 18), 3100, 3200, 30, bekrePal);
        clientService.subscribeToTrainingPlan(bekrePal.getId(), haladoErosito.getId());

        Client gitAron = createClient("Git", "Áron", "git.aron@teszt.com", "gitaron",
                LocalDate.of(1995, 10, 5), 181, Gender.MALE);
        createWeightDiaries(LocalDate.of(2022, 11, 18), 72, 30, 3, true, gitAron);
        createCalorieDiaries(LocalDate.of(2022, 11, 18), 2400, 2500, 30, gitAron);
        clientService.subscribeToTrainingPlan(gitAron.getId(), haladoErosito.getId());

        Client rizOtto = createClient("Riz", "Ottó", "riz.otto@teszt.com", "rizotto",
                LocalDate.of(1997, 7, 1), 175, Gender.MALE);
        createWeightDiaries(LocalDate.of(2022, 11, 18), 58, 30, 2, true, rizOtto);
        createCalorieDiaries(LocalDate.of(2022, 11, 18), 2600, 2650, 30, rizOtto);
        clientService.subscribeToTrainingPlan(rizOtto.getId(), hatEdzes.getId());

        Client mezeiVirag = createClient("Mezei", "Virág", "mezei.virag@teszt.com", "mezeivirag",
                LocalDate.of(1998, 4, 25), 167, Gender.FEMALE);
        createWeightDiaries(LocalDate.of(2022, 11, 18), 60, 30, 2, false, mezeiVirag);
        createCalorieDiaries(LocalDate.of(2022, 11, 18), 2100, 2200, 30, mezeiVirag);
        clientService.subscribeToTrainingPlan(mezeiVirag.getId(), alakformalo.getId());

        Client eszetLenke = createClient("Eszet", "Lenke", "eszet.lenke@teszt.com", "eszetlenke",
                LocalDate.of(1996, 12, 5), 171, Gender.FEMALE);
        createWeightDiaries(LocalDate.of(2022, 11, 18), 67, 30, 3, false, eszetLenke);
        createCalorieDiaries(LocalDate.of(2022, 11, 18), 2300, 2350, 30, eszetLenke);
        clientService.subscribeToTrainingPlan(eszetLenke.getId(), alakformalo.getId());

        Client harMonika = createClient("Har", "Monika", "har.monika@teszt.com", "harmonika",
                LocalDate.of(1992, 3, 30), 168, Gender.FEMALE);
        createWeightDiaries(LocalDate.of(2022, 11, 18), 64, 30, 3, false, harMonika);
        createCalorieDiaries(LocalDate.of(2022, 11, 18), 2250, 2300, 30, harMonika);
        clientService.subscribeToTrainingPlan(harMonika.getId(), kezdoCardio.getId());

        Client kaszaBLanka = createClient("Kasza", "Blanka", "kasza.blanka@teszt.com", "kaszablanka",
                LocalDate.of(1995, 11, 7), 177, Gender.FEMALE);
        createWeightDiaries(LocalDate.of(2022, 11, 18), 58, 30, 3, false, kaszaBLanka);
        createCalorieDiaries(LocalDate.of(2022, 11, 18), 2350, 2400, 30, kaszaBLanka);
        clientService.subscribeToTrainingPlan(kaszaBLanka.getId(), haladoCardio.getId());
    }

    private Coach createCoach(String lastName, String firstName, String email, String password) {
        Coach coach = (Coach) createUser(lastName, firstName, email, password, UserType.COACH);
        return userRepository.save(coach);
    }

    private Client createClient(String lastName, String firstName, String email, String password,
                                LocalDate birth, int height, Gender gender) {
        Client client = (Client) createUser(lastName, firstName, email, password, UserType.CLIENT);
        client.setBirth(birth);
        client.setHeight(height);
        client.setGender(gender);
        return userRepository.save(client);
    }

    private DomainUser createUser(String lastName, String firstName, String email, String password, UserType userType) {
        DomainUser user = userType == UserType.CLIENT ? new Client() : new Coach();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    private void createWeightDiaries(LocalDate startDate, double startWeight, int count, int step,
                                     boolean increase, Client client) {
        createWeightDiary(startWeight, startDate, client);
        for (int i = 1; i < count; i++) {
            LocalDate createDate = startDate.plusDays(i);
            if (i % step == 0) {
                startWeight += increase ? 0.1 : -0.1;
                startWeight = Math.round(startWeight * 10d) / 10d;
            }
            createWeightDiary(startWeight, createDate, client);
        }
    }

    private void createWeightDiary(double weight, LocalDate createdDate, Client client) {
        WeightDiary weightDiary = new WeightDiary();
        weightDiary.setWeight(weight);
        weightDiary.setCreateDate(createdDate);
        weightDiaryService.createDiary(client.getId(), weightDiary);
    }

    private void createCalorieDiaries(LocalDate startDate, int min, int max, int count,
                                      Client client) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            LocalDate createDate = startDate.plusDays(i);
            int calorie = random.nextInt(max - min) + min;
            calorie = (calorie / 10) * 10;
            createCalorieDiary(calorie, createDate, client);
        }
    }

    private void createCalorieDiary(int calories, LocalDate createdDate, Client client) {
        CalorieDiary calorieDiary = new CalorieDiary();
        calorieDiary.setCalories(calories);
        calorieDiary.setCreateDate(createdDate);
        calorieDiaryService.createDiary(client.getId(), calorieDiary);
    }

    private TrainingPlan createTrainingPlan(String name, TrainingType trainingType, int calorieBurn,
                                            int length, Gender recommendation, Coach coach) {
        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlan.setName(name);
        trainingPlan.setTrainingType(trainingType);
        trainingPlan.setCalorieBurn(calorieBurn);
        trainingPlan.setLength(length);
        trainingPlan.setRecommendation(recommendation);
        return trainingPlanService.createTrainingPlan(coach.getId(), trainingPlan);
    }

    private void createRepetitiveExercise(String name, int sets, int rest, TimeUnit restTimeUnit, int reps,
                                          TrainingPlan trainingPlan) {
        RepetitiveExercise exercise = new RepetitiveExercise();
        exercise.setName(name);
        exercise.setSets(sets);
        exercise.setRest(rest);
        exercise.setRestTimeUnit(restTimeUnit);
        exercise.setRepetitions(reps);
        exerciseService.createExercise(exercise, trainingPlan.getId());
    }

    private void createIntervalExercise(String name, int sets, int rest, TimeUnit restTimeUnit, int length,
                                        TimeUnit lengthTimeUnit, TrainingPlan trainingPlan) {
        IntervalExercise exercise = new IntervalExercise();
        exercise.setName(name);
        exercise.setSets(sets);
        exercise.setRest(rest);
        exercise.setRestTimeUnit(restTimeUnit);
        exercise.setLength(length);
        exercise.setLengthTimeUnit(lengthTimeUnit);
        exerciseService.createExercise(exercise, trainingPlan.getId());
    }
}

