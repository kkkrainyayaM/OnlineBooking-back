package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.RouteDto;
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
    public RouteDto add(RouteDto route) {
        Route routePOJO = RouteMapper.INSTANCE.routeDtoToRoute( route );
        return RouteMapper.INSTANCE.routeToRouteDto( routeRepository.save( routePOJO ) );
    }

    @Override
    public RouteDto getById(long id) {
        return RouteMapper.INSTANCE.routeToRouteDto( routeRepository.getOne( id ) );
    }

    @Override
    public List<RouteDto> getAll() {
        return routeRepository.findAll().stream()
                .map( RouteMapper.INSTANCE::routeToRouteDto )
                .collect( Collectors.toList() );
    }

    @Override
    public RouteDto update(RouteDto route) {
        Route updatedRoute = routeRepository.getOne( route.getId() );
        updatedRoute.setDepartureTime( route.getDepartureTime() );
        updatedRoute.setArrivalTime( route.getArrivalTime() );
        updatedRoute.setDepartureTime( route.getDeparturePoint() );
        updatedRoute.setArrivalPoint( route.getArrivalPoint() );
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
