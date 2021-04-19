package me.mdbell.terranet.common.game.messages;

import me.mdbell.terranet.common.util.Color;

public class PlayerInfoMessage extends GameMessage {
    private int id;
    private int skin;
    private int hair;
    private String name;
    private int hairDye;
    private int hideVisual1, hideVisual2;
    private int hideMisc;

    Color hairColor, skinColor, eyeColor, shirtColor, underShirtColor, pantsColor, shoesColor;

    int difficulty;
    int torches;

    public PlayerInfoMessage() {
        super(OP_PLAYER_INFO);
    }

    public int id(){
        return id;
    }

    public PlayerInfoMessage id(int id){
        this.id = id;
        return this;
    }

    public int skin(){
        return skin;
    }

    public PlayerInfoMessage skin(int skin) {
        this.skin = skin;
        return this;
    }

    public int hair(){
        return hair;
    }

    public PlayerInfoMessage hair(int hair) {
        this.hair = hair;
        return this;
    }

    public String name(){
        return name;
    }

    public PlayerInfoMessage name(String name) {
        this.name = name;
        return this;
    }

    public int hairDye(){
        return hairDye;
    }

    public PlayerInfoMessage hairDye(int dye){
        this.hairDye = dye;
        return this;
    }

    public int hideVisual1(){
        return hideVisual1;
    }

    public PlayerInfoMessage hideVisual1(int hideVisual1) {
        this.hideVisual1 = hideVisual1;
        return this;
    }

    public int hideVisual2(){
        return hideVisual2;
    }

    public PlayerInfoMessage hideVisual2(int hideVisual2) {
        this.hideVisual2 = hideVisual2;
        return this;
    }

    public int hideMisc(){
        return hideMisc;
    }

    public PlayerInfoMessage hideMisc(int hideMisc) {
        this.hideMisc = hideMisc;
        return this;
    }

    public Color hairColor(){
        return hairColor;
    }

    public PlayerInfoMessage hairColor(Color color) {
        this.hairColor = color;
        return this;
    }

    public Color skinColor(){
        return skinColor;
    }

    public PlayerInfoMessage skinColor(Color color) {
        this.skinColor = color;
        return this;
    }

    public Color eyeColor(){
        return eyeColor;
    }

    public PlayerInfoMessage eyeColor(Color color) {
        this.eyeColor = color;
        return this;
    }

    public Color shirtColor(){
        return shirtColor;
    }

    public PlayerInfoMessage shirtColor(Color color) {
        this.shirtColor = color;
        return this;
    }

    public Color underShirtColor(){
        return underShirtColor;
    }

    public PlayerInfoMessage underShirtColor(Color color) {
        this.underShirtColor = color;
        return this;
    }

    public Color pantsColor(){
        return pantsColor;
    }

    public PlayerInfoMessage pantsColor(Color color) {
        this.pantsColor = color;
        return this;
    }

    public Color shoesColor(){
        return shoesColor;
    }

    public PlayerInfoMessage shoesColor(Color color) {
        this.shoesColor = color;
        return this;
    }

    public int difficulty(){
        return difficulty;
    }

    public PlayerInfoMessage difficulty(int difficulty){
        this.difficulty = difficulty;
        return this;
    }

    public int torches(){
        return torches;
    }

    public PlayerInfoMessage torches(int torches){
        this.torches = torches;
        return this;
    }

    @Override
    public String toString() {
        return "PlayerInfoMessage{" +
                "id=" + id +
                ", skin=" + skin +
                ", hair=" + hair +
                ", name='" + name + '\'' +
                ", hairDye=" + hairDye +
                ", hideVisual1=" + hideVisual1 +
                ", hideVisual2=" + hideVisual2 +
                ", hideMisc=" + hideMisc +
                ", hairColor=" + hairColor +
                ", skinColor=" + skinColor +
                ", eyeColor=" + eyeColor +
                ", shirtColor=" + shirtColor +
                ", underShirtColor=" + underShirtColor +
                ", pantsColor=" + pantsColor +
                ", shoesColor=" + shoesColor +
                ", difficulty=" + difficulty +
                ", torches=" + torches +
                '}';
    }
}
