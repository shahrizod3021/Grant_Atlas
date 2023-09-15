package it.grantatlas.Service;

import it.grantatlas.Entity.Order;
import it.grantatlas.Entity.Rooms;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Payload.OrderDto;
import it.grantatlas.Repository.OrderRepository;
import it.grantatlas.Repository.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final RoomsRepository roomsRepository;

    private final TelegramService telegramService;

    private final MailSendService mailSendService;

    public ApiResponse ordering(OrderDto orderDto){
        Rooms rooms = roomsRepository.findById(orderDto.roomId()).orElseThrow(() -> new ResourceNotFoundException("siz tanlagan xona uchun buyurtma berib bo'lmadi"));
        Order order = Order.builder()
                .phoneNumber(orderDto.phoneNumber())
                .arrivalDate(orderDto.arrival())
                .departureDate(orderDto.departure())
                .childNumber(orderDto.child())
                .parentsNumber(orderDto.parent())
                .rooms(rooms)
                .build();
        orderRepository.save(order);
        String text = "Yangi buyurtma:\n📲Telefon raqam: " + order.getPhoneNumber() +    "\n🧒Yosh bolalar soni: " + orderDto.child() + "\n👨‍🦰Katta yoshdagilar soni: " + orderDto.parent() + "\n🛬Kelish sanasi: " + orderDto.arrival() + "\n🛫Ketish sanasi: " + orderDto.departure() + "\n®️Xona turi: " + rooms.getNameUz();
        telegramService.sendMessage(text);
        mailSendService.sendToEmail("Yangi buyurtma", text);
        return new ApiResponse("Buyurtmangiz qabul qilindi", true, 200);
    }

    public ApiResponse deleteOrder(UUID id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyurtma topilmagani sababli uni o'chirib bo'lmadi"));
        orderRepository.delete(order);
        return new ApiResponse("Buyurtma olib tashlandi", true, 200);
    }
}
