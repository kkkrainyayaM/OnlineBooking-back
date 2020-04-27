package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.dto.SearchDto;
import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.mappers.RouteMapper;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final PassengerService passengerService;
    private final RouteMapper routeMapper;

    @Override
    public RouteDto add(RouteDto newRoute) {
        Route route = routeMapper.routeDtoToRoute( newRoute );
        log.info( "Route from routeMapper: {}", route );
        Route routeWithId = routeRepository.save( route );
        log.info( "Saved route: {}", route );
        return routeMapper.routeToRouteDto( routeWithId );
    }

    @Override
    public RouteDto getById(long id) {
        log.info( "Route Id = {}", id );
        Route route = routeRepository.findById( id ).get();
        log.info( "Route by Id: {}", route );
        return routeMapper.routeToRouteDto( route );
    }

    @Override
    public List<RouteDto> getAll() {
        List<RouteDto> list = routeRepository.findAll().stream()
                .map( routeMapper::routeToRouteDto )
                .collect( Collectors.toList() );
        log.info( "List<RouteDto>: {}", list );
        return list;
    }

    @Override
    public RouteDto update(RouteDto newRoute) {
        Route route = routeRepository.save( routeMapper.routeDtoToRoute( newRoute ) );
        log.info( "Updated route: {}", route );
        return routeMapper.routeToRouteDto( route );
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByRouteId( id );
        log.info( "Deleted passenger by routeId = {}", id );
        Route route = routeRepository.findById( id ).get();
        routeRepository.delete( route );
        log.info( "Deleted route by id = {}", id );
    }

    @Override
    public List<RouteDto> getBySearch(SearchDto search) {
        Date date = search.getDate();
        String arrivalPoint = search.getArrivalPoint();
        String departurePoint = search.getDeparturePoint();
        log.info( "Search: {}", search );
        List<Route> list = routeRepository.findAllByDateAndArrivalPointAndDeparturePoint(
                date, arrivalPoint, departurePoint );
        log.info( "List of routes by search: {}", list );
        return list.stream().map( routeMapper::routeToRouteDto ).collect( Collectors.toList() );
    }
}
