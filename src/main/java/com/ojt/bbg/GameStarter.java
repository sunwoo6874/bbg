package com.ojt.bbg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameStarter {
    private final static Logger logger = LoggerFactory.getLogger("GameStarter");

    public static void main(String[] args) {
        if (args[0].equals("wiki")) {
            logger.debug("Initiating NamuWiki Numeric Baseball Game.");
            new com.ojt.bbg.namuWiki.BaseBallGame().Game(args[1]); // coin

        } else if (args[0].equals("book")) {
            logger.debug("Initiating HelloCoding Numeric Baseball Game.");
            new com.ojt.bbg.helloCoding.BaseBallGame().Game(args[1]);
        }
    }
}
