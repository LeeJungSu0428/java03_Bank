package main;

import service.BankService;

import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        BankService bSer = new BankService();

        while(run) {
            System.out.println("===== [ ICIB 은행] =====");
            System.out.println("| 1. 계좌등록\n| 2. 잔액조회\n| 3. 입금\n| 4. 출금\n| 5. 입출금 내역조회\n| 0. 종료");
            System.out.print("메뉴선택 > ");
            String menu = sc.next();

            switch (menu) {
                case "1": // 신규계좌 등록
                    bSer.save();
                    break;
                case "2": // 잔액조회
                    bSer.findById();
                    break;
                case "3": // 입금
                    bSer.inMoney();
                    break;
                case "4": // 출금
                    bSer.outMoney();
                    break;
                case "5": // 입출금내역조회
                    bSer.trade();
                    break;
                case "0": // 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.out.println();
                    run = false;
                    break;
                default:
                    System.out.println("0~5 사이의 숫자만 입력가능합니다.");
                    System.out.println();
            }

        }
    }
}
