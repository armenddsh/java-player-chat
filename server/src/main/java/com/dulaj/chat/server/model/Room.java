package com.dulaj.chat.server.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String name;
    private List<Player> players = new ArrayList<>();

    public Room(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(final Player player) {
        this.players.add(player);
    }

    public void removePlayer(final Player player) {
        this.players.remove(player);
    }

    public boolean existsPlayer(final Player player) {
        return this.players.contains(player);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + players +
                '}';
    }
}
