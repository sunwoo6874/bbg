package com.ojt.bbg.helloCoding;

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
 * 
 * 
 * 구현 항목.
 * 1. 랜덤 숫자 3개 어레이로 받기.
 * 2. 숫자 3개 입력 받고 out, strike, ball 인지 알려주기.
 * 3. 최종 결과 알려주기. (xS, yB, zO)
 */
public class BaseBallGame {
    private final static Logger logger = LoggerFactory.getLogger("BaseBallGame");
    private Scanner sc = null;
    private boolean isNotValid = true;

    public void Game(String coin) {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@ Welcome to Numeric baseball game (Book) !! @@@@@@@@@@@@@@@@@@@@@");

        BaseBallGame bbg = new BaseBallGame();
        playBall(bbg, Integer.parseInt(coin));

        logger.debug("@@@@@@@@@@@@@@@@@@@@@  Numeric baseball game END (Wiki)  @@@@@@@@@@@@@@@@@@@@@");

    };

    private void compareScore(BaseBallGame player) {

        player.sc = new Scanner(System.in);

        logger.debug("Play ball! give any input number from 0 ~ 10.");

        int sCount = 0;
        int bCount = 0;
        int oCount = 0;
        int pbc = 0;

        // 랜덤 숫자 3개 뽑기는다..
        final int[] fieldBalls = getRandom();

        for (int j = 0; j < fieldBalls.length; j++) {
            final int pitchBall = throwBall(player);
            logger.debug("Pitch Balls => " + pitchBall);

            // 비교
            pbc = 0;
            while (pbc <= fieldBalls.length) {
                if (j == pbc && pitchBall == fieldBalls[pbc]) {
                    logger.debug("STRIKE!! PlayCount:" + j + " Attack number:" + pitchBall);
                    sCount++;
                    break;

                } else if (j != pbc && pitchBall == fieldBalls[pbc] && pbc <= fieldBalls.length - 1) {
                    logger.debug("BALL!! PlayCount:" + j + " Attack number:" + pitchBall);
                    bCount++;
                    break;

                } else if (pitchBall != fieldBalls[pbc] && pbc == fieldBalls.length - 1) {
                    logger.debug("OUT!! PlayCount:" + j + " Attack number:" + pitchBall);
                    oCount++;
                    break;

                } else {
                    pbc++;
                }
            }
            logger.debug("[ S:" + sCount + " B:" + bCount + " O:" + oCount + " ]"); // 점수판
        }
        logger.debug("Field Balls =>" + fieldBalls[0] + " " + fieldBalls[1] + " " + fieldBalls[2] + "\n");
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

    private int throwBall(BaseBallGame player) {
        int ball = -1;

        do {
            player.isNotValid = true;

            try {
                logger.debug("Throw Ball!");
                player.sc = new Scanner(System.in);
                String input = player.sc.next();
                ball = Integer.parseInt(input);

                if (ball > 0 && ball < 10) { // 유효해서 빠져나옴
                    logger.debug("Ball Thrown!!");
                    player.isNotValid = false;

                } else {
                    logger.debug("Throw number from 0 ~ 9 again.");
                }

            } catch (Exception e) {
                logger.error("Throw the number only.");
            }

        } while (player.isNotValid);

        return ball;
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