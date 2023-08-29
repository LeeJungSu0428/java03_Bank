package repository;


import DTO.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class BankRepository {
    private ArrayList<ClientDTO> cDTOList = new ArrayList<>();


    public void save(ClientDTO cDTO) {
        cDTOList.add(cDTO);
    }

    public List<ClientDTO> findAll() {
        return cDTOList;
    }

    public ClientDTO findByID(String findAccNum) {
        for (ClientDTO clientDTO : cDTOList) {
//            if(findAccNum.equals(clientDTO.getAccountNumber())) {
            if (clientDTO.getAccountNumber().equals(findAccNum)) {
                return clientDTO;
            }
        }
        return null;
    }
}
