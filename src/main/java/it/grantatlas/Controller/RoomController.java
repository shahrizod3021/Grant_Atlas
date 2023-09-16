package it.grantatlas.Controller;

import it.grantatlas.Entity.RoomImg;
import it.grantatlas.Entity.Rooms;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Payload.ResRoom;
import it.grantatlas.Payload.RoomDto;
import it.grantatlas.Repository.RoomImgRepository;
import it.grantatlas.Repository.RoomsRepository;
import it.grantatlas.Service.RoomsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomsService roomsService;

    private final RoomsRepository roomsRepository;

    @GetMapping("/for-user")
    public HttpEntity<?> getRoom(){
        List<ResRoom> rooms = roomsService.getRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Rooms> all = roomsRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Rooms rooms = roomsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Xona topilmadi"));
        return ResponseEntity.ok(rooms);
    }


    @PostMapping
    public HttpEntity<?> addRoom(@RequestBody RoomDto roomDto){
        Integer integer = roomsService.addRoom(roomDto);
        return ResponseEntity.ok(integer);
    }

    @PutMapping("/upload/{id}")
    public HttpEntity<?> uploadPhoto(@PathVariable Integer id, @RequestParam(name = "photoId")UUID photoId){
        ApiResponse apiResponse = roomsService.addImg(id, photoId);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK :HttpStatus.BAD_REQUEST).body(apiResponse);
    }


    @PutMapping("/edit-data/{id}")
    public HttpEntity<?> editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        ApiResponse apiResponse = roomsService.editData(id, roomDto);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRoom(@PathVariable Integer id){
        ApiResponse apiResponse = roomsService.deleteRoom(id);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK :HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping("/img/{id}")
    public HttpEntity<?> deleteImg(@PathVariable Integer id){
        ApiResponse apiResponse = roomsService.deleteImgs(id);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
