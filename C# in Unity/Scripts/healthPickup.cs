using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class healthPickup : MonoBehaviour
{
    public float healthAmount;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter2D(Collider2D other)
    {
        if(other.tag == "Player")
        {
            playerHealth pcHealth = other.gameObject.GetComponent<playerHealth>();

            pcHealth.addHealth(healthAmount);
            Destroy(gameObject);
        }
    }
}
