package edu.miu.carrental.service;

import edu.miu.carrental.AppConfiguration;
import edu.miu.carrental.CarFleetClient;
import edu.miu.carrental.domain.dto.CarsDto;
import edu.miu.carrental.domain.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author bazz
 * Apr 24 2023
 * 18:25
 */
@Service
public class ReservationService {

    @Autowired
    CarFleetClient client;

    @Transactional
    public ReservationDto reserveCar(ReservationDto dto){

        CarsDto carsDto = client.searchCarFromFleet("type", dto.getCarType());
        if(carsDto.getCars().stream().anyMatch(v->v.getAvailable())){


        }
    }

    public CarsDto searchCar(ReservationDto dto) {
        return client.searchCarFromFleet("type", dto.getCarType());
    }
}
