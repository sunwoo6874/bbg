package com.ojt.bbg.namuWiki;

import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * <숫자 야구 게임>
 * 게임 룰.
 * strike: 숫자 위치 맞춘 경우.
 * ball: 숫자는 맞지만 위치가 틀릴 경우.
 * out: 숫자와 위치 모두 틀릴 경우.
 */

public class BaseBallGame {
    private final static Logger logger = LoggerFactory.getLogger("BaseBallGame");
    private Scanner sc = null;
    private boolean isNotValid = true;

    public void Game(String args) {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@ Welcome to Numeric baseball game (Wiki) !! @@@@@@@@@@@@@@@@@@@@@");

        BaseBallGame bbg = new BaseBallGame();
        int coin = Integer.parseInt(args);
        bbg.playBall(bbg, coin);

        logger.debug("@@@@@@@@@@@@@@@@@@@@@  Numeric baseball game END (Wiki)  @@@@@@@@@@@@@@@@@@@@@");
    }

    private int[] throwBall(BaseBallGame player) {
        int[] balls = null;

        do {
            player.isNotValid = true;

            try {
                logger.debug("Throw Ball!");
                player.sc = new Scanner(System.in);
                String input = player.sc.next();
                String tmpBalls[] = String.valueOf(input).split("");

                if (tmpBalls.length == 3) { // 유효해서 빠져나옴

                    balls = new int[tmpBalls.length];
                    for (int i = 0; i < (int) tmpBalls.length; i++) {
                        balls[i] = Integer.parseInt(tmpBalls[i]);
                    }

                    logger.debug("Ball Thrown!!");
                    player.isNotValid = false;

                } else {
                    logger.debug("Throw three digit number again.");
                }

            } catch (Exception e) {
                logger.error("Throw the number only.");
            }

        } while (player.isNotValid);

        return balls;
    }

    private void compareScore(BaseBallGame p) {
        final int[] fieldBalls = p.getRandom();
        int sCount = 0;
        int bCount = 0;

        int[] pitchBalls = throwBall(p);
        if (pitchBalls == null || pitchBalls.length == 0) {
            logger.error("Ball is null.");
            return;
        }

        logger.debug("Pitch Balls => " + pitchBalls[0] + " " + pitchBalls[1] + " " + pitchBalls[2]);

        // debug only
        // fieldBalls[0] = 0;
        // fieldBalls[1] = 1;
        // fieldBalls[2] = 2;

        // 비교
        for (int pitchBIdx = 0; pitchBIdx < pitchBalls.length; pitchBIdx++) {

            for (int fieldBIdx = 0; fieldBIdx < fieldBalls.length; fieldBIdx++) {

                if (fieldBIdx == pitchBIdx && pitchBalls[pitchBIdx] == fieldBalls[fieldBIdx]) {
                    // logger.debug("STRIKE!! PlayCount:" + fieldBIdx + " Attack number:" +
                    // pitchBalls[0] + " " + pitchBalls[1] + " " + pitchBalls[2]);
                    sCount++;
                    continue;

                } else if (fieldBIdx != pitchBIdx && pitchBalls[pitchBIdx] == fieldBalls[fieldBIdx]) {
                    // logger.debug("BALL!! PlayCount:" + fieldBIdx + " Attack number:" +
                    // pitchBalls[0] + " " + pitchBalls[1] + " " + pitchBalls[2]);
                    bCount++;
                    continue;

                }
            }
        }

        // 점수판
        logger.debug("Field Balls => " + fieldBalls[0] + " " + fieldBalls[1] + " " + fieldBalls[2]); // for debug
        if (sCount >= 1 || bCount >= 1) {
            logger.debug("[ S:" + sCount + " B:" + bCount + " ]");
        } else {
            logger.debug("[ OUT ]"); // 점수판
        }

    }

    private void playBall(BaseBallGame player, int gameCoin) {
        logger.debug("Play ball! give any number from 0 ~ 9.");

        if (gameCoin < 0) {
            logger.debug("[Endless Mode] Press \'Ctrl + c\' to exit the game.");
        } else {
            logger.debug("Game coin:" + gameCoin);
        }

        if (gameCoin < 0) { // 무한 플레이
            while (true) {
                player.compareScore(player);
            }

        } else { // 코인 갯수 대로 플레이
            for (int i = 0; i < gameCoin; i++) {
                player.compareScore(player);
            }
            player.sc.close();
        }
    }

    private int[] getRandom() {
        // 랜덤 숫자 3개 뽑기는다..
        Random random = new Random();
        int arr[] = new int[3];
        int count = 0;

        // 난수 생성.
        while (count < 3) {
            int ranNo = random.nextInt(9);
            boolean isDuplicate = false;

            for (int n = 0; n < count; n++) {
                if (arr[n] == ranNo) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                arr[count] = ranNo;
                count++;
            }
        }
        return arr;
    }

}
