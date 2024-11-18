using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class cameraFollow2D : MonoBehaviour
{
    public Transform target; // Target is what the camera will follow. The target will always be the player character
    public float smoothing; // Variable for dampening effect that occurs in the camera for smoother motion when following the target

    Vector3 offset; // 3D Vector difference between camera and target

    float lowY; // Lowest point the camera will follow the target

    // Start is called before the first frame update
    void Start()
    {
        offset = transform.position - target.position;

        lowY = transform.position.y - 10;
    }

    // Runs everytime the game is interacted with
    void FixedUpdate()
    {
        Vector3 targetCamPosition = target.position + offset;

        transform.position = Vector3.Lerp(transform.position, targetCamPosition, smoothing * Time.deltaTime);

        if(transform.position.y < lowY)
        {
            transform.position = new Vector3(transform.position.x, lowY, transform.position.z);
        }
    }
}
