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
        providers.add(new Provider("United Health Care1", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care2", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care3", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care4", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care5", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care6", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care7", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care8", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care9", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care10", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care11", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care12", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care13", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care14", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care15", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care16", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care17", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care18", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care19", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care20", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care21", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care22", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care23", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care24", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care25", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care26", new String[]{"United PPO", "United PPO Plus"}));
        providers.add(new Provider("United Health Care27", new String[]{"United PPO", "United PPO Plus"}));
    }

    public static Provider provider(String name) {
        return providers.get(providers.indexOf(new Provider(name, null)));
    }

    public static class Provider {
        public final String name;
        public final List<Plan> plans = new ArrayList<>();

        public Provider(String name, String[] plans) {
            this.name = name;
            if (plans != null) {
                for (String plan : plans) {
                    this.plans.add(new Plan(plan));
                }
            }
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Provider provider = (Provider) o;

            return name.equals(provider.name);

        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    public static class Plan {
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
