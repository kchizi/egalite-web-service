package com.bfsi.mfi.vo;

import java.util.Date;

import com.bfsi.mfi.entity.DepositMaturityTxnTracker;

public class DepositMaturityTxnTrackerVO extends MaintenanceVO<DepositMaturityTxnTracker>{
	public DepositMaturityTxnTrackerVO()
	{
		entity=new DepositMaturityTxnTracker();
	}
	
	public DepositMaturityTxnTrackerVO(DepositMaturityTxnTracker entity) {
		super(entity);
	}
	public String getMbsTxnId() {
		return entity.getId();
	}

	public void setMbsTxnId(String mbsTxnId) {
		entity.setId(mbsTxnId);
	}
	
	public String getAgendaId() {
		return entity.getAgendaId();
	}

	public void setAgendaId(String agendaId) {
		entity.setAgendaId(agendaId);

	}
	public String getTxnCode()
	{
		return entity.getTxnCode();
	}
	public void setTxnCode(String txnCode)
	{
		entity.setTxnCode(txnCode);	
		
	}

	public long getSeqNo() {
		return entity.getSeqNo();
	}

	public void setSeqNo(long seqNo) {
		entity.setSeqNo(seqNo);

	}
	
	public String getCbsAcRefNo() {
		return entity.getCbsAcRefNo();
	}

	public void setCbsAcRefNo(String cbsAcRefNo) {
		entity.setCbsAcRefNo(cbsAcRefNo);

	}

	public String getBranchCode() {
		return entity.getBranchCode();
	}

	public void setBranchCode(String branchCode) {
		entity.setBranchCode(branchCode);

	}

	public String getCustomerId() {
		return entity.getCustomerId();
	}

	public void setCustomerId(String customerId) {
		entity.setCustomerId(customerId);

	}

	public String getCustomerFullName() {
		return entity.getCustomerFullName();
	}

	public void setCustomerFullName(String customerFullName) {
		entity.setCustomerFullName(customerFullName);

	}

	public String getAgentId() {
		return entity.getAgentId();
	}

	public void setAgentId(String agentId) {
		entity.setAgentId(agentId);

	}

	public String getAgentName() {
		return entity.getAgentName();
	}

	public void setAgentName(String agentName) {
		entity.setAgentName(agentName);

	}

	public String getLocationCode() {
		return entity.getLocationCode();
	}

	public void setLocationCode(String locationCode) {
		entity.setLocationCode(locationCode);

	}

	public String getLocationDesc() {
		return entity.getLocationDesc();
	}

	public void setLocationDesc(String locationDesc) {
		entity.setLocationDesc(locationDesc);

	}
	public Double getMaturityAmount() {
		return entity.getMaturityAmount();
	}

	public void setMaturityAmount(Double maturityAmount) {
		entity.setMaturityAmount(maturityAmount);

	}
	
	public Double getAmountPaidOut() {
		return entity.getAmountPaidOut();
	}

	public void setAmountPaidOut(Double amountPaidOut) {
		entity.setAmountPaidOut(amountPaidOut);

	}
	
	public String getTxnNarrative() {
		return entity.getTxnNarrative();
	}

	public void setTxnNarrative(String txnNarrative) {
		entity.setTxnNarrative(txnNarrative);

	}

	public Date getTxnInitTime() {
		return entity.getTxnInitTime();
	}

	public void setTxnInitTime(Date txnInitTime) {
		entity.setTxnInitTime(txnInitTime);

	}

	public Date getTxnSyncTime() {
		return entity.getTxnSyncTime();
	}

	public void setTxnSyncTime(Date txnSyncTime) {
		entity.setTxnSyncTime(txnSyncTime);

	}
	public String getTxnStatusDesc() {
		return entity.getTxnStatusDesc();
	}

	public void setTxnStatusDesc(String txnStatusDesc) {
		entity.setTxnStatusDesc(txnStatusDesc);

	}
	
	public String getTxnErrCode() {
		return entity.getTxnErrCode();
	}

	public void setTxnErrCode(String txnErrCode) {
		entity.setTxnErrCode(txnErrCode);
		
	}

	public String getTxnErrMessage() {
		return entity.getTxnErrMessage();
	}

	public void setTxnErrMessage(String txnErrMessage) {
		entity.setTxnErrMessage(txnErrMessage);
	}
/*For Tag No : EGA-MN15-000021 Starts*/
	
	public String getGeneratedSms()  {
		return entity.getGeneratedSms();
	}
	public void setGeneratedSms(String generatedSms) {
		entity.setGeneratedSms(generatedSms);
	}
	
	public String getSmsMobileNo()  {
		return entity.getSmsMobileNo();
	}
	public void setSmsMobileNo(String smsMobileNo) {
		entity.setSmsMobileNo(smsMobileNo);
	}
	
	public String getSmsContent() {
		return entity.getGeneratedSms();
	}
	public void setSmsContent(String smsContent) {
		entity.setSmsContent(smsContent);
	}
	
	public String getGeneratedRevr()  {
		return entity.getGeneratedRevr();
	}
	public void setGeneratedRevr(String generatedRevr) {
		entity.setGeneratedRevr(generatedRevr);
	}
	
	public String getFullPartInd()  {
		return entity.getFullPartInd();
	}
	public void setFullPartInd(String fullPartInd) {
		entity.setFullPartInd(fullPartInd);
	}
	
	public String getSyncType()  {
		return entity.getSyncType();
	}
	public void setSyncType(String syncType) {
		entity.setSyncType(syncType);
	}
	/*For Tag No : EGA-MN15-000021 End*/
	

}
