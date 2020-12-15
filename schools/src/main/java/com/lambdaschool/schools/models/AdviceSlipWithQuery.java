package com.lambdaschool.schools.models;

import java.util.List;

public class AdviceSlipWithQuery
{
    private int total_results;
    private String query;
    private List<Slip> slips;

    public AdviceSlipWithQuery()
    {
    }

    public int getTotal_results()
    {
        return total_results;
    }

    public void setTotal_results(int total_results)
    {
        this.total_results = total_results;
    }

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public List<Slip> getSlips()
    {
        return slips;
    }

    public void setSlips(List<Slip> slips)
    {
        this.slips = slips;
    }
}
