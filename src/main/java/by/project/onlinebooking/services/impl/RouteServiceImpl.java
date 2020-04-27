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
import lombok.val;
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
    public RouteDto add(RouteDto routeDto) {
        log.info("Save route: {}", routeDto);
        val route = routeMapper.routeDtoToRoute(routeDto);
        val savedRoute = routeRepository.save(route);
        return routeMapper.routeToRouteDto(savedRoute);
    }

    @Override
    public RouteDto getById(long id) {
        log.info("Route Id = {}", id);
        Route route = routeRepository.findById(id).get();
        log.info("Get route by Id: {}", route);
        return routeMapper.routeToRouteDto(route);
    }

    @Override
    public List<RouteDto> getAll() {
        val list = routeRepository.findAll().stream()
                .map(routeMapper::routeToRouteDto)
                .collect(Collectors.toList());
        log.info("Get list of routes: {}", list);
        return list;
    }

    @Override
    public RouteDto update(RouteDto newRoute) {
        Route route = routeRepository.save(routeMapper.routeDtoToRoute(newRoute));
        log.info("Update route: {}", route);
        return routeMapper.routeToRouteDto(route);
    }

    @Override
    public void delete(long id) {
        passengerService.deleteByRouteId(id);
        log.info("Delete passenger by routeId = {}", id);
        Route route = routeRepository.findById(id).get();
        routeRepository.delete(route);
        log.info("Delete route by id = {}", id);
    }

    @Override
    public List<RouteDto> getBySearch(SearchDto search) {
        Date date = search.getDate();
        String arrivalPoint = search.getArrivalPoint();
        String departurePoint = search.getDeparturePoint();
        log.info("Search: {}", search);
        val list = routeRepository.findAllByDateAndArrivalPointAndDeparturePoint(
                date, arrivalPoint, departurePoint);
        log.info("List of routes by search: {}", list);
        return list.stream().map(routeMapper::routeToRouteDto).collect(Collectors.toList());
    }
}
