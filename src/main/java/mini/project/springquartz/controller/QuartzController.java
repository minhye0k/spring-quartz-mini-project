package mini.project.springquartz.controller;

import lombok.RequiredArgsConstructor;
import mini.project.springquartz.service.QuartzService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuartzController {
    private final QuartzService quartzService;

    @PostMapping("{seq}")
    public ResponseEntity postQuartzJob(@PathVariable Long seq) {
        quartzService.postQuartzJob(seq);
        return ResponseEntity.ok().build();
    }
}
