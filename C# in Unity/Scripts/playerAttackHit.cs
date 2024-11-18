using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playerAttackHit : MonoBehaviour
{
    public float attackDamage; // Variable for player attack damage

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter2D(Collider2D other) // When the object this script is attached to collides with something...
    {
        if (other.gameObject.layer == LayerMask.NameToLayer("Attackable"))
        {
            Destroy(gameObject);
            //Debug.Log("Attack hit TriggerEnter"); // Created for development purposes to check if code is working

            if(other.tag == "Enemy")
            {
                enemyHealth damageEnemy = other.gameObject.GetComponent<enemyHealth>();
                damageEnemy.addDamage(attackDamage);
            }
        }
    }

    void OnTriggerStay2D(Collider2D other) // When the object this script is attached to is inside something with something...
    {
        if (other.gameObject.layer == LayerMask.NameToLayer("Attackable"))
        {
            Destroy(gameObject);
            //Debug.Log("Attack hit TriggerStay"); // Created for development purposes to check if code is working

            if(other.tag == "Enemy")
            {
                enemyHealth damageEnemy = other.gameObject.GetComponent<enemyHealth>();
                damageEnemy.addDamage(attackDamage);
            }
        }
    }
}
