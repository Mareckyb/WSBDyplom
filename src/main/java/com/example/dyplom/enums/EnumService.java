package com.example.dyplom.enums;

import org.springframework.stereotype.Service;

@Service
public class EnumService {

    public int getStateNameSize()
    {
        return StateName.values().length;
    }

    public int getTypeOfIssueSize()
    {
        return TypeOfIssue.values().length;
    }

    public boolean chceckStateNameExists(String state) {

        try {
            StateName.valueOf(state.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
