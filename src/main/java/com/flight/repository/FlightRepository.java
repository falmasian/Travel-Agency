//package com.flight.repository;
//
//import com.flight.entity.City;
//import com.flight.entity.FlightInfo;
//
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.criteria.*;
//import jakarta.persistence.EntityManager;
//import org.springframework.stereotype.Repository;
//
/*import java.util.List;

@Repository
public class FlightRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<FlightInfo> getFlightsBetweenCities() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FlightInfo> criteriaQuery = criteriaBuilder.createQuery(FlightInfo.class);
        Root<FlightInfo> flightRoot = criteriaQuery.from(FlightInfo.class);

        // Join with departure city
        Join<FlightInfo, City> departureCityJoin = flightRoot.join("departureCity");
        Predicate departureCityPredicate = criteriaBuilder.equal(departureCityJoin.get("id"), flightRoot.);

        // Join with arrival city
        Join<Flight, City> arrivalCityJoin = flightRoot.join("arrivalCity");
        Predicate arrivalCityPredicate = criteriaBuilder.equal(arrivalCityJoin.get("id"), arrivalCityId);

        // Combine the predicates with AND
        Predicate finalPredicate = criteriaBuilder.and(departureCityPredicate, arrivalCityPredicate);

        // Add the WHERE clause to the query
        criteriaQuery.where(finalPredicate);*/
//
//        // Execute the query and return the result list
//        return entityManager.createQuery(criteriaQuery).getResultList();
//    }
//}
