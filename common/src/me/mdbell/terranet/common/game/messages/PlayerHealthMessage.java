package me.mdbell.terranet.common.game.messages;

public class PlayerHealthMessage extends GameMessage {
    private int id;
    private int hp;
    private int maxHp;
    public PlayerHealthMessage() {
        super(OP_PLAYER_HP);
    }

    public int id(){
        return id;
    }

    public PlayerHealthMessage id(int id){
        this.id = id;
        return this;
    }

    public int hp(){
        return hp;
    }

    public PlayerHealthMessage hp(int hp){
        this.hp = hp;
        return this;
    }

    public int maxHp(){
        return maxHp;
    }

    public PlayerHealthMessage maxHp(int maxHp){
        this.maxHp = maxHp;
        return this;
    }

    @Override
    public String toString() {
        return "PlayerHealthMessage{" +
                "id=" + id +
                ", hp=" + hp +
                ", maxHp=" + maxHp +
                '}';
    }
}
