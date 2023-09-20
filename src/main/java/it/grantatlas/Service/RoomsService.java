package it.grantatlas.Service;

import it.grantatlas.Entity.Order;
import it.grantatlas.Entity.RoomImg;
import it.grantatlas.Entity.Rooms;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Payload.ResRoom;
import it.grantatlas.Payload.RoomDto;
import it.grantatlas.Repository.OrderRepository;
import it.grantatlas.Repository.RoomImgRepository;
import it.grantatlas.Repository.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomsService {

    private final RoomsRepository roomsRepository;

    private final RoomImgRepository roomImgRepository;

    private final OrderRepository orderRepository;


    public List<ResRoom> getRooms() {
        List<ResRoom> resRooms = new ArrayList<>();
        for (Rooms rooms : roomsRepository.findAll()) {
            ResRoom resRoom = ResRoom.builder().rooms(rooms).build();
            for (RoomImg roomImg : rooms.getRoomImgList()) {
                resRoom.setLastImg(roomImg.getRoomImg());
            }
            resRooms.add(resRoom);
        }
        return resRooms;
    }

    public Integer addRoom(RoomDto roomDto) {
        Rooms build = Rooms.builder()
                .description(roomDto.descriptionUz())
                .descriptionRu(roomDto.descriptionRu())
                .descriptionEng(roomDto.descriptionEng())
                .descriptionTurk(roomDto.descriptionTurk())
                .size(roomDto.size())
                .howMuchRoom(roomDto.howMuch())
                .howMany(roomDto.howMany())
                .build();
        build.setNameUz(roomDto.nameUz());
        build.setNameRu(roomDto.nameRu());
        build.setNameEng(roomDto.nameEng());
        build.setNameTurk(roomDto.nameTurk());
        build.setRoomImgList(new ArrayList<>());
        Rooms rooms = roomsRepository.save(build);
        return rooms.getId();
    }


    public ApiResponse addImg(Integer roomId, UUID img) {
        Rooms rooms = roomsRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("siz kiritgan xona topilmadi"));
        RoomImg roomImg = RoomImg.builder().roomImg(img).build();
        RoomImg roomImg1 = roomImgRepository.save(roomImg);
        rooms.getRoomImgList().add(roomImg1);
        roomsRepository.save(rooms);
        return new ApiResponse("Xona saqlandi", true, 201);
    }

    public ApiResponse deleteRoom(Integer roomId) {
        Rooms rooms = roomsRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("siz tanlagan xona topilmadi"));
        List<RoomImg> roomImgs = new ArrayList<>(rooms.getRoomImgList());
        List<Order> orderByRoomsId = orderRepository.findOrderByRoomsId(roomId);
        for (Order order : orderByRoomsId) {
            order.setRooms(null);
            orderRepository.save(order);
        }
        roomsRepository.delete(rooms);
        roomImgRepository.deleteAll(roomImgs);
        return new ApiResponse("Xona olib tashlandi", true, 200);
    }

    public ApiResponse editData(Integer roomId, RoomDto roomDto) {
        Rooms rooms = roomsRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Siz tanlagan xona topilmadi"));
        rooms.setNameUz(roomDto.nameUz().length() != 0 ? roomDto.nameUz() : rooms.getNameUz());
        rooms.setNameRu(roomDto.nameRu().length() != 0 ? roomDto.nameRu() : rooms.getNameRu());
        rooms.setNameEng(roomDto.nameEng().length() != 0 ? roomDto.nameEng() : rooms.getNameEng());
        rooms.setNameTurk(roomDto.nameTurk().length() != 0 ? roomDto.nameTurk() : rooms.getNameTurk());
        rooms.setDescription(roomDto.descriptionUz().length() != 0 ? roomDto.descriptionUz() : rooms.getDescription());
        rooms.setDescriptionEng(roomDto.descriptionEng().length() != 0 ? roomDto.descriptionEng() : rooms.getDescriptionEng());
        rooms.setDescriptionRu(roomDto.descriptionRu().length() != 0 ? roomDto.descriptionRu() : rooms.getDescriptionRu());
        rooms.setDescriptionTurk(roomDto.descriptionTurk().length() != 0 ? roomDto.descriptionTurk() : rooms.getDescriptionTurk());
        rooms.setHowMuchRoom(roomDto.howMuch().length() != 0 ? roomDto.howMuch() : rooms.getHowMuchRoom());
        rooms.setHowMany(roomDto.howMany().length() != 0 ? roomDto.howMany() : rooms.getHowMany());
        rooms.setSize(roomDto.size().length() != 0 ? roomDto.size() : rooms.getSize());
        roomsRepository.save(rooms);
        return new ApiResponse("Ma'lumotlar taxrirlandi", true, 200);
    }

    public ApiResponse deleteImgs(Integer roomId) {
        Rooms rooms = roomsRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("siz tanlagan xona topilmadi"));
        List<RoomImg> roomImgs = new ArrayList<>();
        for (RoomImg roomImg : rooms.getRoomImgList()) {
            RoomImg rasmTopilmadi = roomImgRepository.findById(roomImg.getId()).orElseThrow(() -> new ResourceNotFoundException("rasm topilmadi"));
            roomImgs.add(rasmTopilmadi);
        }
        rooms.setRoomImgList(new ArrayList<>());
        roomsRepository.save(rooms);
        roomImgRepository.deleteAll(roomImgs);
        return new ApiResponse("Rasmlar olib tashlandi", true, 200);
    }
}
