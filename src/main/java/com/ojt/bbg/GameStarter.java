package com.ojt.bbg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameStarter {
    private final static Logger logger = LoggerFactory.getLogger("GameStarter");

    public static void main(String[] args) {
        logger.debug("Initiating " + args[0] + " Numeric Baseball Game.");

        if (args[0].equals("wiki")) {
            new com.ojt.bbg.namuWiki.BaseBallGame().Game(args[1]); // coin
        } else if (args[0].equals("book")) {
            new com.ojt.bbg.helloCoding.BaseBallGame().Game(args[1]);
        }
    }
}
