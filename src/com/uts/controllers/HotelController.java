package com.uts.controllers;

import com.uts.model.*;

import java.util.ListIterator;
import java.util.Scanner;
import com.uts.services.HotelService;


public class HotelController {

    final HotelService hotel = new HotelService();

    public void runSeed() {
        this.hotel.addRoom("Room 1", "delux", 1, "");
        this.hotel.addRoom("Room 2", "standar", 1, "");
        this.hotel.addRoom("Room 3", "single", 1, "");
        this.hotel.addRoom("Room 4", "double", 1, "");
        this.hotel.addRoom("Room 5", "triple", 1, "");
    }

    public void addRoom(Scanner scan) {
        String roomName;
        String roomType;
        Integer roomMaxUser;
        String keyLoc = "";

        System.out.println("=== Input data hotel baru ===");
        System.out.print("Nama ruangan  :");
        roomName = scan.nextLine();
        System.out.print("Jenis         :");
        roomType = scan.nextLine();
        System.out.print("Maksimal User :");
        roomMaxUser = Integer.parseInt(scan.nextLine());
        System.out.print("Location [last,fist,index,blank]: ");
        keyLoc = scan.nextLine();

        try {
            hotel.addRoom(roomName, roomType, roomMaxUser, Integer.parseInt(keyLoc) - 1);
        } catch (Exception e) {
            hotel.addRoom(roomName, roomType, roomMaxUser, keyLoc);
        }

    }

    private void viewAsRoom(Room room, Integer index) {
        if(index != null) {
            System.out.println("Id            : " + index + 1);
        }
        System.out.println("Nama Ruangan  : " + room.name);
        System.out.println("Jenis Ruangan : " + room.type);
        System.out.println("Maksimal User : " + room.maxUser);
        System.out.println("--- Users ---");

        Integer userIndex = 0;
        for (User user : room.users) {
            System.out.println("No            : " + userIndex + 1);
            System.out.println("Nama Pengguna : " + user.name);
            userIndex++;
        }
        System.out.println("====================");
    }

    public void viewRoom() {
        System.out.println("=== Daftar Room ===");
        Integer loopIndex = 0;
        for (Room room : this.hotel.getRooms()) {
            this.viewAsRoom(room, loopIndex);
            loopIndex++;
        }
    }

    public void deleteRoom(Scanner scan) {
        String key;
        System.out.println("=== Hapus Ruangan ===");
        System.out.print("Masukan keyword [nama]: ");
        key = scan.nextLine();
        hotel.removeRoom(key);
    }

    public void allocateuserToRoom(Scanner scan) {
        String keyword;
        System.out.println("=== Checkin User ===");
        System.out.print("Masukan keyword ruangan [nama] : ");
        keyword = scan.nextLine();
        Room currentRoom = hotel.findRoom(keyword);
        if(currentRoom != null) {
            String allocateNext = "y";
            while (allocateNext.equalsIgnoreCase("y")) {
                String userName = "";
                System.out.print("Masukan nama pengguna : ");
                userName = scan.nextLine();
                currentRoom.addUser(new User(userName));
                System.out.print("Masukan user lain [y/n]: ");
                allocateNext = scan.nextLine();
            }
        } else {
            System.out.println("Ruangan tidak di temukan");
        }
    }

    public void checkoutRoom(Scanner scan) {
        String keyword;
        System.out.println("=== Checkout  ===");
        System.out.print("Masukan keyword ruangan [nama] : ");
        keyword = scan.nextLine();
        Room currentRoom = hotel.findRoom(keyword);
        if(currentRoom != null) {
            currentRoom.removeAllUser();
        } else {
            System.out.println("Ruangan tidak di temukan");
        }
    }

    public void detailEveryRoom(Scanner scan) {
        String arg = "next";
        ListIterator roomIterate = hotel.getRooms().listIterator();

        while(!arg.isBlank()) {
            try {
                Runtime.getRuntime().exec("clear");
            } catch(Exception e) {
                // empty
            }

            if(arg.equalsIgnoreCase("next") && roomIterate.hasNext()) {
                Integer nextIndex = roomIterate.nextIndex();
                roomIterate.next();
                Room currentRoom = hotel.findRoom(Integer.toString(nextIndex));
                if(currentRoom != null) {
                    this.viewAsRoom(currentRoom, null);
                }

            } else if(arg.equalsIgnoreCase("prev") && roomIterate.hasPrevious()) {
                roomIterate.previous();
                Integer nextIndex = roomIterate.previousIndex();
                Room currentRoom = hotel.findRoom(Integer.toString(nextIndex));
                if(currentRoom != null) {
                    this.viewAsRoom(currentRoom, null);
                }
            } else {
                System.out.println("No Data");
                break;
            }

            System.out.print("Next/Prev/Blank[exit] : ");
            arg = scan.nextLine();
        }

    }




}
