package com.autentia.service.group;

import com.autentia.domain.Expense;
import com.autentia.domain.User;
import com.autentia.service.group.dto.GroupDTO;

import java.util.List;

public interface GroupService {

    /**
     * Get all the groups.
     *
     * @return the list of group DTOs.
     */
    List<GroupDTO> findAll();

    /**
     * Get the group by id.
     *
     * @param id the id of the group.
     * @return the group DTO.
     */
    GroupDTO findById(Long id);

    /**
     * Create a new group.
     *
     * @param groupId the id of the group.
     * @param user    the user that creates the group.
     * @return the group DTO.
     */
    GroupDTO addUser(Long groupId, User user);

    /**
     * Add a new expense to a group.
     *
     * @param groupId the id of the group.
     * @param expense the expense to add.
     * @return the group DTO.
     */
    GroupDTO addExpense(Long groupId, Expense expense);
}
