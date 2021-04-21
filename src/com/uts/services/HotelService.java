package com.uts.services;

import java.util.LinkedList;
import com.uts.model.*;



public class HotelService {
    final Integer maxRoomEveryFloor = 2;
    final LinkedList<Room> rooms = new LinkedList<>();

    public LinkedList<Room> getRooms() {
        return this.rooms;
    }

    public void addRoom(String name, String type, Integer maxUser, String position) {
        if(position == "first") {
            this.rooms.addFirst(new Room(name, type, maxUser));
        } else {
            this.rooms.addLast(new Room(name, type, maxUser));
        }
    }

    public void addRoom(String name, String type, Integer maxUser, Integer index) {
        this.rooms.add(index, new Room(name, type, maxUser));
    }

    public void removeRoom(String key) {
       this.rooms.removeIf((Room room) -> room.name.equalsIgnoreCase(key));
    }

    public Room findRoom(String key) {
        Integer loopIndex = 0;
        for (Room room : this.rooms) {
            if(room.name.equalsIgnoreCase(key) || loopIndex == Integer.parseInt(key)) {
                return room;
            }
            loopIndex++;
        }
        return null;
    }

}
