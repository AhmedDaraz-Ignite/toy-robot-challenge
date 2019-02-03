package com.challenge.puzzle.toyrobot;

import com.challenge.puzzle.toyrobot.exceptions.ToyRobotException;
import com.challenge.puzzle.toyrobot.types.ToyRobotDto;
import lombok.var;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
class ToyRobotController {

    private ToyRobotService service;
    private ToyRobotAssembler assembler;

    public ToyRobotController(ToyRobotService service, ToyRobotAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @PostMapping("/toys")
    ResponseEntity<Resource<ToyRobotDto>> place(@RequestBody ToyRobotDto toy) throws ToyRobotException {

        ToyRobotDto savedToyRobotDto = service.placeToyRobot(toy);

        Resource<ToyRobotDto> toyResource = assembler.toResource(savedToyRobotDto);
        URI location = URI.create(toyResource.getId().expand().getHref());

        return ResponseEntity.created(location).body(toyResource);
    }

    @GetMapping("/toys/{id}")
    ResponseEntity<Resource<ToyRobotDto>> report(@PathVariable long id) throws ToyRobotException {

        var toyRobotDTO = service.reportToyRobot(id);
        var toyResource = assembler.toResource(toyRobotDTO);

        return ResponseEntity.status(HttpStatus.OK).body(toyResource);
    }

    @PutMapping("/toys/{id}/move")
    ResponseEntity<Void> move(@PathVariable long id) throws ToyRobotException {

        service.moveToyRobot(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/toys/{id}/left")
    ResponseEntity<Void> left(@PathVariable long id) throws ToyRobotException {

        service.leftRotateToyRobot(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/toys/{id}/right")
    ResponseEntity<Void> right(@PathVariable long id) throws ToyRobotException {

        service.rightRotateToyRobot(id);
        return ResponseEntity.noContent().build();
    }

}

