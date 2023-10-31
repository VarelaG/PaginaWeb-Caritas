package com.ppyl.Caritas.controller;

import com.ppyl.Caritas.model.Event;
import com.ppyl.Caritas.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private EventService eventService;

    @GetMapping("/calendar")
    public String showCalendarPage(Model model) {
        // Lógica para cargar eventos y pasarlos a la vista.
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "/admin/event/calendario";
    }

    @PostMapping("/calendar/event")
    public String createEvent(@ModelAttribute Event event) {
        eventService.createEvent(event);
        return "redirect:/admin/event/calendario";
    }

    @GetMapping("/calendar/event/{eventId}")
    public String showEventDetails(@PathVariable Long eventId, Model model) {
        // Lógica para mostrar los detalles de un evento.
        Event event = eventService.getEventById(eventId);
        model.addAttribute("event", event);
        return "event-details";
    }

    @PutMapping("/calendar/event/{eventId}")
    public String updateEvent(@PathVariable Long eventId, @ModelAttribute Event event) {
        // Lógica para actualizar un evento en la base de datos.
        eventService.updateEvent(event);
        return "redirect:/calendar";
    }

    @DeleteMapping("/calendar/event/{eventId}")
    public String deleteEvent(@PathVariable Long eventId) {
        // Lógica para eliminar un evento de la base de datos.
        eventService.deleteEvent(eventId);
        return "redirect:/calendar";
    }
}
