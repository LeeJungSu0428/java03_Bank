package service;

import DTO.ClientDTO;
import repository.BankRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class BankService {
    Scanner sc = new Scanner(System.in);
    BankRepository bankRep = new BankRepository();

    // 계좌등록
    public void save() {
        ClientDTO cDTO = new ClientDTO();
        boolean firstMoney = true;
        boolean chk = true;

        System.out.println("[ 계좌발급] ");

//        while(chk){
//            System.out.print("사용할 계좌번호 입력 > ");
//            String newAccNum = sc.next();
//            for(ClientDTO clientDTO:cDTOList) {
//                if(clientDTO.getAccountNumber() != null) {
//                    if(newAccNum.equals(clientDTO.getAccountNumber())) {
//                        System.out.println("이미 존재하는 계좌번호입니다.");
//                    }
//                    chk = false;
//                } else {
//                    chk = false;
//                }
//            } return newAccNum;
//            cDTO.getAccountNumber(newAccNum);
//        }

        System.out.print("사용할 계좌번호 입력 > ");
        String newAccNum = sc.next();
        cDTO.setAccountNumber(newAccNum);


        System.out.print("사용할 비밀번호 입력 > ");
        String newAccPass = sc.next();
        cDTO.setClientPass(newAccPass);

        System.out.print("계좌주 입력 > ");
        String newName = sc.next();
        cDTO.setClientName(newName);

        while (firstMoney) {
            System.out.println(" < 최초입금액은 1000원부터 가능합니다. > ");
            System.out.print("최초입금액 입력 >");
            int firstBal = sc.nextInt();
            if (firstBal < 1000) {
                System.out.println("1000원 이상을 입금해주세요");
                System.out.println();
            } else {
                cDTO.setBalance(firstBal);
                firstMoney = false;
            }
        }


        System.out.println("계좌생성이 완료되었습다.");
        bankRep.save(cDTO);

    }
    // 계좌조회

    public void findById() {
        System.out.print("조회 할 계좌번호 입력 > ");
        String findAccNum = sc.next();
        ClientDTO result = bankRep.findByID(findAccNum);
        if (result == null) {
            System.out.println("없는 계좌번호입니다.");
        } else {
            int findBal = result.getBalance();
            System.out.println(findAccNum + "의 잔액은 " + findBal + "원 입니다.");
        }
    }

    public void findAll() {
        for (ClientDTO clientDTO : bankRep.findAll()) {
            System.out.println(clientDTO);
        }
    }

    public void inMoney() {
//        int setBal = 0;
        System.out.print("입금 할 계좌번호 > ");
        String inMoneyAcc = sc.next();
        ClientDTO result = bankRep.findByID(inMoneyAcc);
        if (result == null) {
            System.out.println("없는 계좌번호입니다.");
        } else {
            System.out.print("입금 금액 입력 > ");
            int inMoney = sc.nextInt();
//            setBal = inMoney + result.getBalance();

            result.setBalance(inMoney + result.getBalance());
            System.out.println("입금 후 금액 " + result.getBalance() + "원");
            System.out.println("입금이 완료되었습니다.");
        }


//        for (ClientDTO cDTO : cDTOList) {
//            if (!(inMoneyAcc.equals(cDTO.getAccountNumber()))) {
//                System.out.println("없는 계좌번호입니다.");
//            } else {
//                System.out.print("입금 금액 입력 > ");
//                int inMoney = sc.nextInt();
//                setBal = inMoney + cDTO.getBalance();
//                System.out.println("입금 후 금액 " + setBal + "원");
//                System.out.println("입금이 완료되었습니다.");
//                cDTO.setBalance(setBal);
//            }
//
//        }


    }

    public void outMoney() {
        boolean run = true;
        while (run) {
            System.out.print("출금 할 계좌번호 입력 > ");
            String outAcc = sc.next();
            ClientDTO result = bankRep.findByID(outAcc);
            if (result == null) {
                System.out.println("없는 계좌번호입니다.");
            } else {
                System.out.print("비밀번호 입력 > ");
                String accPass = sc.next();
                if (!(accPass.equals(result.getClientPass()))) {
                    System.out.println("비밀번호가 틀렸습니다");
                } else {
                    System.out.print("입금 할 계좌번호 입력 > ");
                    String inAccNum = sc.next();
                    if (!(inAccNum.equals(result.getAccountNumber()))) {
                        System.out.println("없는 계좌번호입니다.");
                    } else {
                        System.out.print("입금받을 금액 입력 > ");
                        int moveMoney = sc.nextInt();
                        int moveDon = moveMoney + result.getBalance();
                        result.setBalance(moveDon);
                        run = false;
                        break;
                    }

                }
            }
        }
    }




    public void trade() { // 1 전체내역, 2 입금내역, 3 출금내역
        System.out.print("조회 할 계좌번호 > ");
        String findAcc = sc.next();

        ClientDTO result = bankRep.findByID(findAcc);
        if(result == null) {
            System.out.println("없는 계좌번호입니다.");
        } else {
                System.out.println("| 1. 전체내역조회\n| 2. 입금내역조회\n| 3. 출금내역조회");
                String choice = sc.next();
                switch (choice) {
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    default:

                }
            }
        }
    }

