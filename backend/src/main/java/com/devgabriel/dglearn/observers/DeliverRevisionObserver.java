package com.devgabriel.dglearn.observers;

import com.devgabriel.dglearn.entities.Deliver;

public interface DeliverRevisionObserver {

	void onSaveRevision(Deliver deliver);
}
