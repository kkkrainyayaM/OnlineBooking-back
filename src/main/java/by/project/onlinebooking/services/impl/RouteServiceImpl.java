package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.mappers.RouteMapper;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final PassengerService passengerService;
    private final RouteMapper routeMapper;

    @Override
    public RouteDto add(RouteDto newRoute) {
        Route route = routeMapper.routeDtoToRoute( newRoute );
        return routeMapper.routeToRouteDto( routeRepository.save( route ) );
    }

    @Override
    public RouteDto getById(long id) {
        return routeMapper.routeToRouteDto( routeRepository.findById( id ).get() );
    }

    @Override
    public List<RouteDto> getAll() {
        return routeRepository.findAll().stream()
                .map( routeMapper::routeToRouteDto )
                .collect( Collectors.toList() );
    }

    @Override
    public RouteDto update(RouteDto route) {
        routeRepository.save( routeMapper.routeDtoToRoute( route ) );
        return route;
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByRouteId( id );
        Route route = routeRepository.findById( id ).get();
        routeRepository.delete( route );
    }

    @Override
    public List<RouteDto> getBySearch(RouteDto route) {
        return routeRepository.findAllByDateAndArrivalPointAndDeparturePoint( route.getDate(),
                route.getArrivalPoint(), route.getDeparturePoint() ).stream()
                .map( routeMapper::routeToRouteDto )
                .collect( Collectors.toList() );
    }
}
