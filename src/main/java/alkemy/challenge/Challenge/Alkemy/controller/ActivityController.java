package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Activity;
import alkemy.challenge.Challenge.Alkemy.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PreAuthorize("hasRole(ROLE_ADMIN)")
    @PostMapping("/activities")
    public ResponseEntity<Activity> createActivities(@RequestBody @Valid Activity activities, BindingResult result) {

        if (result.hasErrors()) {
            return (ResponseEntity<Activity>) ResponseEntity.notFound();
        }
        activityService.save(activities);
        return ResponseEntity.ok(activities);
    }


    /*@GetMapping(path = "/{name_id}")
    public Optional<Activity> getActivitiesByID(@PathVariable Long id) {
        Optional<Activity> a = ActivityService.getActivitiesByID(id);
        if (null == a) {
            throw new ActivitiesNotFoundException("Activities with ID[" + id + "] not found");
        }
        return a;
    }
    */

    /*Endpoint para actualizar actividades */
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivities(@PathVariable(value = "id") Long activitiesId,
                                                     @Valid @RequestBody Activity activityDetails) {
        return activityService.updateActivities(activitiesId, activityDetails);
    }
}
