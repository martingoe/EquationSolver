module RESTView {
    exports de.cubearrow.restview.services;

    requires spring.web;
    requires model;
    requires spring.boot.autoconfigure;
    opens de.cubearrow.restview.services to spring.core;
}