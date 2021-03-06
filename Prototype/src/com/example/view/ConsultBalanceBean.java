package com.example.view;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import com.example.infrastructure.HibernateEntryRepository;
import com.example.model.Entry;
import com.example.repository.EntryRepository;
import com.example.service.BusinessRuleException;
import com.example.service.EntryManager;
import com.example.util.FacesUtil;
import com.example.util.Repositories;

@ManagedBean
public class ConsultBalanceBean {
	
	private Repositories repositories = new Repositories();
	private Entry selectedEntry;
	private List<Entry> entries = new ArrayList<Entry>();

	@SuppressWarnings("unchecked")
	@PostConstruct 
	public void initialize() {
		EntryRepository entryRepository = this.repositories.getEntries();
		this.entries = entryRepository.listAll();
			}
	
	public void remove(){
		EntryManager entryManager = new EntryManager(this.repositories.getEntries());
		try {
			entryManager.remove(this.selectedEntry);
			
			this.initialize();
			FacesUtil.addMsg(FacesMessage.SEVERITY_INFO, "Removed Successfully!");
		} 
		catch (BusinessRuleException e) {
			FacesUtil.addMsg(FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		
	}
	
	public List<Entry> getEntries() {
		return entries;
	}

	public Entry getSelectedEntry() {
		return selectedEntry;
	}

	public void setSelectedEntry(Entry selectedEntry) {
		this.selectedEntry = selectedEntry;
	}
	
	
		
}
