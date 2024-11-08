package com.csc.settlement.service;

import com.csc.settlement.pojos.request.SettlementRequest;
import com.csc.settlement.pojos.response.SettlementResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ISettlementService {
    SettlementResponse initiateSettlement(SettlementRequest settlementRequest, HttpServletRequest request);
}
