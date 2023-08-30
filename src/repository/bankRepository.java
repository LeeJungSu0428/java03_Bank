package repository;

import DTO.*;

import java.util.ArrayList;
import java.util.List;

public class bankRepository {
    List<ClientDTO> clientDTOList = new ArrayList<>();
    List<AccountDTO> accountDTOList = new ArrayList<>();

    /**
     * 계좌번호를 받아
     * 해당계좌의 정보를 반환
     * 없으면 null
     * @param accountNumber 계좌번호
     * @return 해당 계좌에 대한 정보
     */
    public ClientDTO checkByAccountNumber(String accountNumber) {
        for(ClientDTO clientDTO : clientDTOList){
            if(clientDTO.getAccountNumber().equals(accountNumber))
                return clientDTO;
        }
        return null;
    }

    /**
     * @return 전체 계좌를 반환
     */
    public List<ClientDTO> findAll() {
        return clientDTOList;
    }

    /**
     * 계좌생성정보 저장
     * @param clientDTO 저장할 계좌정보
     * @return 저장 성공유무
     */
    public boolean clientSave(ClientDTO clientDTO) {
        return clientDTOList.add(clientDTO);
    }

    /**
     * 입금
     * @param accountNumber 입금할 계좌번호
     * @param inBalance 임금 금액
     */
    public void inBalance(String accountNumber, int inBalance) {
        for(ClientDTO clientDTO : clientDTOList){
            if(clientDTO.getAccountNumber().equals(accountNumber)) {
                clientDTO.setBalance(clientDTO.getBalance() + inBalance);
                break;
            }
        }
    }

    /**
     * 거래내역 저장
     * @param accountDTO 거래내역
     * @return 저장 성공 유무
     */
    public boolean accountSave(AccountDTO accountDTO) {
        return accountDTOList.add(accountDTO);
    }

    /**
     * 출금
     * @param accountNumber 출금할 계좌
     * @param outBalance 출금 금액
     */
    public void outBalance(String accountNumber, int outBalance) {
        for(ClientDTO clientDTO : clientDTOList){
            if(clientDTO.getAccountNumber().equals(accountNumber)) {
                clientDTO.setBalance(clientDTO.getBalance() - outBalance);
                break;
            }
        }
    }

    /**
     * 입출금내역
     * @param accountNumber 계좌번호
     * @return  해당계좌의 입출금 내역
     */
    public List<AccountDTO> findAllRecordByAccount(String accountNumber) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for(AccountDTO accountDTO : accountDTOList){
            if(accountDTO.getAccountNumber().equals(accountNumber))
                accountDTOS.add(accountDTO);
        }
        return accountDTOS;
    }

    /**
     * 입금내역
     * @param accountNumber 계좌번호
     * @return 해당계좌의 입금내역
     */
    public List<AccountDTO> findInbalanceRecordByAccount(String accountNumber) {
        List<AccountDTO> inBalanceList = new ArrayList<>();
        for(AccountDTO accountDTO : accountDTOList){
            if(accountDTO.getAccountNumber().equals(accountNumber) && accountDTO.getWithdraw() == 0) // 입금은 출금금액이 0
                inBalanceList.add(accountDTO);
        }
        return inBalanceList;
    }

    /**
     * 출금 내역
     * @param accountNumber 계좌번호
     * @return 해당계좌의 출금내역
     */
    public List<AccountDTO> findOutBalanceRecordByAccount(String accountNumber) {
        List<AccountDTO> outBalanceList = new ArrayList<>();
        for(AccountDTO accountDTO : accountDTOList){
            if(accountDTO.getAccountNumber().equals(accountNumber) && accountDTO.getDeposit() == 0) // 출금은 입금금액이 0
                outBalanceList.add(accountDTO);
        }
        return outBalanceList;
    }
}