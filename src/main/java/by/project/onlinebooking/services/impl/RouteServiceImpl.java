package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.entities.Route;
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
    public Route getById(long id) {
        return routeRepository.getOne( id );
    }

    @Override
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route update(Route route) {
        Route updatedRoute = routeRepository.getOne( route.getId() );
        updatedRoute.setTimeDeparture( route.getTimeDeparture() );
        updatedRoute.setTimeArrival( route.getTimeArrival() );
        updatedRoute.setPointDeparture( route.getPointDeparture() );
        updatedRoute.setPointArrival( route.getPointArrival() );
        updatedRoute.setDate( route.getDate() );
        return routeRepository.save( updatedRoute );
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByRouteId( id );
        Route route = routeRepository.getOne( id );
        routeRepository.delete( route );
    }
}
