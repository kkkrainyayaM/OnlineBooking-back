package by.project.onlinebooking.exceptions;

public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException(Long id){
        super("Could not find route " + id);
    }
}
