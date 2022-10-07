package org.example.database;

import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.types.StorageManager;
import org.example.database.model.Company;

public final class DB
{

    private static StorageManager INSTANCE = null;

    private DB()
    {
    }

    private static StorageManager createStorageManager(Company root)
    {
        StorageManager result  = EmbeddedStorageConfiguration.load("storage.xml")
                .createEmbeddedStorageFoundation()
                .setRoot(root)
                .createEmbeddedStorageManager()
                .start();

        initData(result, root);
        return result;
    }

    private static void initData(StorageManager storageManager, Company root)
    {
        //if (root.getProducts().isEmpty()) {
        //init Data?!?!
        // Store entire object graph, unconditionally
        //Storer storer = storageManager.createEagerStorer();
        //storer.store(root);
        //  storer.commit();
        //}
    }

    public static StorageManager getInstance()
    {
        // This is not thread safe but for training, we assume only 1 user, the developer and thus no concurrency.
        if (INSTANCE == null)
        {
            Company root = initRoot();
            INSTANCE = createStorageManager(root);
        }
        return INSTANCE;
    }

    private static Company initRoot()
    {
        Company root = new Company();
        return root;
    }

    public static Company getRoot()
    {
        // This is not thread safe but for training, we assume only 1 user, the developer and thus no concurrency.
        if (INSTANCE == null)
        {
            Company root = initRoot();
            INSTANCE = createStorageManager(root);
        }
        return (Company) INSTANCE.root();
    }
}
