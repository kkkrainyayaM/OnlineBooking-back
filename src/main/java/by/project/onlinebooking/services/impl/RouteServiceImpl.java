package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.DTO.RouteDTO;
import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.mappers.RouteMapper;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("RouteService")
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final PassengerService passengerService;

    public RouteServiceImpl(RouteRepository routeRepository,
                            PassengerServiceImpl passengerService) {
        this.routeRepository = routeRepository;
        this.passengerService = passengerService;
    }

    @Override
    public RouteDTO add(RouteDTO route) {
        Route routePOJO = RouteMapper.INSTANCE.routeDtoToRoute( route );
        return RouteMapper.INSTANCE.routeToRouteDto( routeRepository.save( routePOJO ) );
    }

    @Override
    public RouteDTO getById(long id) {
        return RouteMapper.INSTANCE.routeToRouteDto( routeRepository.getOne( id ) );
    }

    @Override
    public List<RouteDTO> getAll() {
        return routeRepository.findAll().stream()
                .map( RouteMapper.INSTANCE::routeToRouteDto )
                .collect( Collectors.toList() );
    }

    @Override
    public RouteDTO update(RouteDTO route) {
        Route updatedRoute = routeRepository.getOne( route.getId() );
        updatedRoute.setTimeDeparture( route.getDepartureTime() );
        updatedRoute.setTimeArrival( route.getArrivalTime() );
        updatedRoute.setPointDeparture( route.getDeparturePoint() );
        updatedRoute.setPointArrival( route.getArrivalPoint() );
        updatedRoute.setDate( route.getDate() );
        return RouteMapper.INSTANCE.routeToRouteDto( routeRepository.save( updatedRoute ) );
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByRouteId( id );
        Route route = routeRepository.getOne( id );
        routeRepository.delete( route );
    }
}
