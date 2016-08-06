package com.jellsoft.mobile.docfin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atulanand on 8/1/16.
 */
public class Insurance {

    public static final List<Provider> providers = new ArrayList<>();
    static {
        providers.add(new Provider("Aetna", new String[]{"Choice PPO", "Choice PPO Plus"}));
        providers.add(new Provider("Cigna", new String[]{"Cigna PPO", "Cigna PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care", new String[]{"United PPO", "United PPO Plus"}));
    }

    public static class Provider
    {
        public final String name;
        public final List<Plan> plans = new ArrayList<>();

        public Provider(String name, String [] plans) {
            this.name = name;
            if(plans!= null)
            {
                for(String plan: plans)
                {
                    this.plans.add(new Plan(plan));
                }
            }
        }

        @Override
        public String toString() {
            return this.name;
        }


    }

    public static class Plan
    {
        public Plan(String name) {
            this.name = name;
        }
        public final String name;

        @Override
        public String toString() {
            return this.name;
        }

    }
}
