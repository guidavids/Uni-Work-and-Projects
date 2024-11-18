using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class primalWraithAttackHit : MonoBehaviour
{
 public float PWAttackDamage; // Variable for player attack damage

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
        if (other.tag == "Player")
        {
            Destroy(gameObject);
            //Debug.Log("Attack hit TriggerEnter"); // Created for development purposes to check if code is working

            if(other.tag == "Player")
            {
                playerHealth damagePlayer = other.gameObject.GetComponent<playerHealth>();
                damagePlayer.playerTakeDamage(PWAttackDamage);  
            }
        }
    }

    void OnTriggerStay2D(Collider2D other) // When the object this script is attached to is inside something with something...
    {
        if (other.tag == "Player")
        {
            Destroy(gameObject);
            //Debug.Log("Attack hit TriggerEnter"); // Created for development purposes to check if code is working

            if(other.tag == "Player")
            {
                playerHealth damagePlayer = other.gameObject.GetComponent<playerHealth>();
                damagePlayer.playerTakeDamage(PWAttackDamage);  
            }
        }
    }
}
