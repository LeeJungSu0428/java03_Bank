package service;

import DTO.*;
import repository.bankRepository;

import java.util.List;
import java.util.Scanner;

public class BankService {
    Scanner scanner = new Scanner(System.in);
    repository.bankRepository bankRepository = new bankRepository();       /// repository 폴더에 있는 bankRepository 클래스를 사용해 bankRepository 라는 객체를 만듦

    /**
     * 계좌 생성
     */
    public void clientSave() {
        System.out.print("계좌주> ");
        String clientName = scanner.next();
        String accountNumber = "";
        while (true) {
            System.out.print("계좌번호> ");
            accountNumber = scanner.next();
            ClientDTO checkClientDTO = bankRepository.checkByAccountNumber(accountNumber);  // 중복체크
            if (checkClientDTO == null) {
                break;
            } else {
                System.out.println("이미 사용중인 계좌입니다.");
            }
        }
        System.out.print("계좌비밀번호> ");
        String clientPass = scanner.next();
        ClientDTO clientDTO = new ClientDTO(clientName, accountNumber, clientPass);
        if (bankRepository.clientSave(clientDTO)) {
            System.out.println("계좌생성");
        } else {
            System.out.println("생성실패");
        }
    }

    /**
     * 전체계좌 조회
     */
    public void findAll() {
        List<ClientDTO> clientDTOList = bankRepository.findAll();
        for (ClientDTO clientDTO : clientDTOList) {
            System.out.println(clientDTO);
        }
    }

    /**
     * 잔액조회
     */
    public void checkbalance() {
        System.out.print("조회할 계좌번호> ");
        String accountNumber = scanner.next();
        ClientDTO clientDTO = bankRepository.checkByAccountNumber(accountNumber);
        if (clientDTO == null) {
            System.out.println("없는 계좌입니다.");
        } else {
            System.out.println(clientDTO.getBalance() + "원");
        }
    }

    /**
     * 샘플데이터 생성
     */
    public void sampleData() {
        for (int i = 1; i < 11; i++) {
            ClientDTO clientDTO = new ClientDTO("clientName" + i, "account" + i, "pass" + i);
            bankRepository.clientSave(clientDTO);
        }
    }

    /**
     * 입금
     */
    public void inBalance() {
        System.out.print("입금할 계좌번호> ");
        String accountNumber = scanner.next();
        System.out.print("입금할 금액> ");
        int inBalance = scanner.nextInt();
        ClientDTO clientDTO = bankRepository.checkByAccountNumber(accountNumber);
        if (clientDTO == null) {
            System.out.println("없는 계좌입니다.");
        } else {
            AccountDTO accountDTO = new AccountDTO(accountNumber, inBalance, 0);
            if (bankRepository.accountSave(accountDTO)) {
                bankRepository.inBalance(accountNumber, inBalance);
            } else {
                System.out.println("입금실패");
            }
        }
    }

    /**
     * 출금
     */
    public void outBalance() {
        System.out.print("출금할 계좌번호> ");
        String accountNumber = scanner.next();
        System.out.print("계좌 비밀번호> ");
        String accountPass = scanner.next();
        System.out.print("출금할 금액> ");
        int outBalance = scanner.nextInt();
        ClientDTO clientDTO = bankRepository.checkByAccountNumber(accountNumber);
        if (clientDTO == null) {
            System.out.println("없는 계좌입니다.");
        } else {
            if (clientDTO.getAccountPass().equals(accountPass)) {
                if (clientDTO.getBalance() >= outBalance) {
                    AccountDTO accountDTO = new AccountDTO(accountNumber, 0, outBalance);
                    if (bankRepository.accountSave(accountDTO)) {
                        bankRepository.outBalance(accountNumber, outBalance);
                    } else {
                        System.out.println("출금 실패");
                    }
                } else {
                    System.out.println("잔액이 부족합니다.");
                }
            } else {
                System.out.println("입력정보가 틀렸습니다.");
            }
        }
    }

    /**
     * 입출금 내역 조회
     */
    public void findAccountRecord() {
        System.out.print("계좌번호> ");
        String accountNumber = scanner.next();
        ClientDTO clientDTO = bankRepository.checkByAccountNumber(accountNumber);
        if (clientDTO == null) {
            System.out.println("없는 계좌입니다.");
        } else {
            while (true) {
                System.out.println("1.전체내역 | 2.입금내역 | 3.출금내역 | 0.종료");
                int sel = scanner.nextInt();
                if (sel == 1) {
                    List<AccountDTO> accountDTOList = bankRepository.findAllRecordByAccount(accountNumber);
                    for (AccountDTO accountDTO : accountDTOList)
                        System.out.println(accountDTO);
                } else if (sel == 2) {
                    List<AccountDTO> inBalanceList = bankRepository.findInbalanceRecordByAccount(accountNumber);
                    for (AccountDTO accountDTO : inBalanceList)
                        System.out.println(accountDTO);
                } else if (sel == 3) {
                    List<AccountDTO> outBalanceList = bankRepository.findOutBalanceRecordByAccount(accountNumber);
                    for (AccountDTO accountDTO : outBalanceList) {
                        System.out.println(accountDTO);
                    }
                } else if (sel == 0) {
                    break;
                } else {
                    System.out.println("다시입력");
                }
            }
        }
    }
}
