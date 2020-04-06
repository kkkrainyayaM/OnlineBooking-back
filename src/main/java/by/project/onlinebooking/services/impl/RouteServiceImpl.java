package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.exceptions.RouteNotFoundException;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.services.PassengerService;
import by.project.onlinebooking.services.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RouteService")
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final PassengerService passengerService;

    public RouteServiceImpl(RouteRepository routeRepository, PassengerServiceImpl passengerService) {
        this.routeRepository = routeRepository;
        this.passengerService = passengerService;
    }

    @Override
    public Route add(Route route) {
        return routeRepository.save( route );
    }

    @Override
    public Route getById(long id) throws RouteNotFoundException {
        return routeRepository.findById( id ).orElseThrow( () -> new RouteNotFoundException( id ) );
    }

    @Override
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route update(Route route) throws RouteNotFoundException {
        Route updatedRoute = routeRepository.findById( route.getId() )
                .orElseThrow( () -> new RouteNotFoundException( route.getId() ) );
        updatedRoute.setTimeDeparture( route.getTimeDeparture() );
        updatedRoute.setTimeArrival( route.getTimeArrival() );
        updatedRoute.setPointDeparture( route.getPointDeparture() );
        updatedRoute.setPointArrival( route.getPointArrival() );
        updatedRoute.setDate( route.getDate() );
        return routeRepository.save( updatedRoute );
    }

    @Override
    public void delete(long id) throws RouteNotFoundException {
        passengerService.deleteByRouteId( id );
        Route route = routeRepository.findById( id )
                .orElseThrow( () -> new RouteNotFoundException( id ) );
        routeRepository.delete( route );
    }
}
