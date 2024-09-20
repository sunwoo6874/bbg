package com.ojt.bbg;

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

    public static void main(String[] args) {
        logger.debug("Numeric baseball game start !! ");

        Scanner sc = new Scanner(System.in);

        // 랜덤 숫자 3개 뽑기는다..
        Random random = new Random();
        final int defNum[] = new int[3];
        int count = 0;

        while (count < 3) {
            int ranNo = random.nextInt(10);
            boolean isDuplicate = false;

            for (int i = 0; i < count; i++) {
                if (defNum[i] == ranNo) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                defNum[count] = ranNo;
                count++;
            }
        }

        logger.debug("Play ball! give any input number from 0 ~ 10.");

        int sCount = 0;
        int bCount = 0;
        int oCount = 0;
        int k = 0;

        for (int j = 0; j < defNum.length; j++) {
            final int ofNum = sc.nextInt();
            logger.debug("=> " + ofNum);
            // 비교
            k = 0;
            while (k <= defNum.length) {
                if (j == k && ofNum == defNum[k]) {
                    logger.debug("STRIKE!! PlayCount:" + j + " Attack number:" + ofNum);
                    sCount++;
                    break;
                } else if (j != k && ofNum == defNum[k] && k <= defNum.length - 1) {
                    logger.debug("BALL!! PlayCount:" + j + " Attack number:" + ofNum);
                    bCount++;
                    break;
                } else if (ofNum != defNum[k] && k == defNum.length - 1) {
                    logger.debug("OUT!! PlayCount:" + j + " Attack number:" + ofNum);
                    oCount++;
                    break;
                } else {
                    k++;
                    continue;
                }
            }
            logger.debug("[ S:" + sCount + " B:" + bCount + " O:" + oCount + " ]"); // 점수판
        }
        logger.debug("Catcher number:" + defNum[0] + " " + defNum[1] + " " + defNum[2]);
        sc.close();
    };
}