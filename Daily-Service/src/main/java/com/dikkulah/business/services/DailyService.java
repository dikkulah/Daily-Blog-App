package com.dikkulah.business.services;

import com.dikkulah.business.dto.DailyDto;
import com.dikkulah.data.entity.Daily;
import com.dikkulah.data.repository.DailyRepository;
import com.dikkulah.exception.AlreadyInUseException;
import com.dikkulah.exception.ResourceNotFoundException;
import com.dikkulah.exception.TokenNotValidException;
import com.dikkulah.feign.GatewayClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class DailyService {

    private static final String THERE_IS_NO_DAILY_WITH_THIS_ID = " there is no daily with this id.";
    private final DailyRepository dailyRepository;
    private final ModelMapper modelMapper;
    private final GatewayClient gatewayClient;

    public DailyDto entityToDto(Daily daily) {
        return modelMapper.map(daily, DailyDto.class);
    }

    public Daily dtoToEntity(DailyDto dailyDto) {
        return modelMapper.map(dailyDto, Daily.class);
    }

    private String validToken(String gatewayToken) {
        return gatewayClient.validate(gatewayToken, gatewayToken).getBody();
    }

    public DailyDto createDaily(DailyDto dailyDto, String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            Daily daily = dtoToEntity(dailyDto);
            if (dailyRepository.findById(dailyDto.getId()).isPresent())
                throw new AlreadyInUseException("There is daily with this id: ".concat(dailyDto.getId()));
            else {
                dailyRepository.save(daily);
                return dailyDto;
            }
        } else throw new TokenNotValidException();

    }

    public List<DailyDto> listUserDaily(String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            List<Daily> dailies = dailyRepository.findByEmail(isTokenValid);
            List<DailyDto> dtoList = new ArrayList<>();
            dailies.forEach(daily -> dtoList.add(entityToDto(daily)));
            return dtoList;
        } else throw new TokenNotValidException();
    }

    public DailyDto findDaily(String id, String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            Daily daily = dailyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.concat(THERE_IS_NO_DAILY_WITH_THIS_ID)));
            return entityToDto(daily);
        } else throw new TokenNotValidException();
    }

    public String deleteDaily(String id, String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            Daily daily = dailyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.concat(THERE_IS_NO_DAILY_WITH_THIS_ID)));
            dailyRepository.delete(daily);
            return "Deleted";
        } else throw new TokenNotValidException();
    }

    public DailyDto updateDaily(String id, DailyDto dailyDto, String gatewayToken) {
        var isTokenValid = validToken(gatewayToken);
        if (isTokenValid != null) {
            Daily daily = dailyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.concat(THERE_IS_NO_DAILY_WITH_THIS_ID)));
            if (daily != null) {
                dailyRepository.save(dtoToEntity(dailyDto));
            }
            return dailyDto;
        } else throw new TokenNotValidException();
    }
}
