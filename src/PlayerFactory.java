public class PlayerFactory {
    PlayerFactory() {}

    /**
     * a Player factory that returns instance of the player requested.
     * @param playerType name of the player type.
     */
    public Player buildPlayer(String playerType){
        switch (playerType) {
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "clever":
                return new CleverPlayer();
            case "snartypamts":
                return new SnartypamtsPlayer();
            default:
                return null;
        }
    }
}
