using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class soulTearFunctionality : MonoBehaviour
{
    enemyHealth PWHealth;

    // Start is called before the first frame update
    void Start()
    {
        PWHealth = GetComponentInParent<enemyHealth>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter2D(Collider2D other)
    {
        if(other.tag == "Player")
        {
            PWHealth.addDamage(20f);
        }
    }
}
