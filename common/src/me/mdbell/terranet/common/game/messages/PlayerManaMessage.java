package me.mdbell.terranet.common.game.messages;

public class PlayerManaMessage extends GameMessage {

    private int id;
    private int mana;
    private int maxMana;

    public PlayerManaMessage() {
        super(OP_PLAYER_MANA);
    }

    public int id(){
        return id;
    }

    public PlayerManaMessage id(int id){
        this.id = id;
        return this;
    }

    public int mana(){
        return mana;
    }

    public PlayerManaMessage mana(int mana){
        this.mana = mana;
        return this;
    }

    public int maxMana(){
        return maxMana;
    }

    public PlayerManaMessage maxMana(int maxMana){
        this.maxMana = maxMana;
        return this;
    }

    @Override
    public String toString() {
        return "PlayerManaMessage{" +
                "id=" + id +
                ", mana=" + mana +
                ", maxMana=" + maxMana +
                '}';
    }
}
