using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class primalWraithAttack : MonoBehaviour
{
    Animator primalWAnim;
    public GameObject primalSlashBox;
    public Transform PWAttack;

    // Start is called before the first frame update
    void Start()
    {
        primalWAnim = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter2D(Collider2D other)
    {
        if(other.tag == "Player")
        {
            primalWAnim.SetTrigger("primalSlash");
        }
    }

    void GenerateAttack()
    {
        Instantiate(primalSlashBox, PWAttack.position, Quaternion.Euler(new Vector3(0, 0, 0)));
    }
}
