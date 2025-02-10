package com.webculcate.eventservicecore.service.external.user;

import com.webculcate.eventservicecore.model.external.user.UserBulkRequest;
import com.webculcate.eventservicecore.model.external.user.UserBulkResponse;
import com.webculcate.eventservicecore.model.external.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.webculcate.eventservicecore.utility.ServiceHelper.nullHandledExtraction;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceExt {
    
    private final UserServiceClient userServiceClient;
    
    public List<UserDto> resolveUsers(Set<Long> userIds) {
        if(userIds.isEmpty())
            return Collections.EMPTY_LIST;
        List<UserDto> userDtoList = getUserDtoBulk(userIds);
        int difference = userIds.size() - userDtoList.size();
        if(difference > 0)
            log.info("unable to resolve " + difference + " users");
        return userDtoList;
    }

    private List<UserDto> getUserDtoBulk(Set<Long> userIds) {
        ResponseEntity<UserBulkResponse> response = userServiceClient.getUserBulk(new UserBulkRequest(userIds));
        Optional<List<UserDto>> optionalUserDtoList = nullHandledExtraction(() -> response.getBody().getUserList());
        return optionalUserDtoList.orElse(Collections.EMPTY_LIST);
    }

}
