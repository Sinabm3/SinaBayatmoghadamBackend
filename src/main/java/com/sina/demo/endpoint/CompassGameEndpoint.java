package com.sina.demo.endpoint;

import com.sina.demo.endpoint.dto.CompassGameDto;
import com.sina.demo.endpoint.dto.InfoCompassGameDto;
import com.sina.demo.service.CompassGameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/compassGame")
public class CompassGameEndpoint {

    private final CompassGameService compassGameService;

    @Autowired
    public CompassGameEndpoint(CompassGameService compassGameService) {
        this.compassGameService = compassGameService;
    }

    @GetMapping
    public Stream<InfoCompassGameDto> getAllCompassGames() {
        return compassGameService.getAllCompassGames();
    }

    @GetMapping(value = "/{id}")
    public CompassGameDto getCompassGameById(@PathVariable Long id) {
        return compassGameService.getCompassGameById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create (@Valid @RequestBody CompassGameDto compassGameDto){
       return compassGameService.create(compassGameDto);
    }

    @PutMapping
    public Long edit(@Valid @RequestBody CompassGameDto compassGameDto, @RequestParam String password){
        return compassGameService.edit(compassGameDto, password);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id, @RequestParam String password){
        compassGameService.delete(id, password);
    }

    @GetMapping("/search")
    public Stream<InfoCompassGameDto> search(@RequestParam String name){
        return compassGameService.search(name);
    }

}
